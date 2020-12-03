package com.example.smartalarm.database;

import android.app.Application;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import com.example.smartalarm.dao.DelayedEventDao;
import com.example.smartalarm.dao.ImmediateEventDao;
import com.example.smartalarm.event.DelayedEvent;
import com.example.smartalarm.event.Event;
import com.example.smartalarm.event.ImmediateEvent;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

public class EventRepository {
   private final DelayedEventDao DED;
   private final ImmediateEventDao IED;
   private final LiveData<List<Event>> mEvents;

   @RequiresApi(api = Build.VERSION_CODES.N)
   public EventRepository(Application app){
      SmartAlarmDatabase db = SmartAlarmDatabase.getDatabase(app);
      DED = db.delayedEventDao();
      IED = db.immediateEventDao();
      mEvents = getAllEvents();
   }

   public LiveData<List<Event>> getEvents(){
      return mEvents;
   }

   public void insert(Event event){
      if(event instanceof ImmediateEvent) {
         new EventRepository.AsyncTask().execute(() -> IED.insert((ImmediateEvent) event));
      }
      else if(event instanceof DelayedEvent){
         new EventRepository.AsyncTask().execute(() -> DED.insert((DelayedEvent) event));
      }
   }

   public void update(Event event){
      if(event instanceof ImmediateEvent) {
         new EventRepository.AsyncTask().execute(() -> IED.update((ImmediateEvent) event));
      }
      else if(event instanceof DelayedEvent){
         new EventRepository.AsyncTask().execute(() -> DED.update((DelayedEvent) event));
      }
   }

   //Mediator Live data:
   // https://developer.android.com/reference/androidx/lifecycle/MediatorLiveData
   @RequiresApi(api = Build.VERSION_CODES.N)
   private LiveData<List<Event>> getAllEvents(){
      MediatorLiveData<List<Event>> ret = new MediatorLiveData<>();
      LiveData<List<ImmediateEvent>> immediate = IED.getAllEvents();
      LiveData<List<DelayedEvent>> delayed = DED.getAllEvents();
      ret.addSource(immediate, value -> ret.setValue(value.stream().map(x -> (Event)x).collect(Collectors.toList())));
      ret.addSource(delayed, value -> ret.setValue(value.stream().map(x -> (Event)x).collect(Collectors.toList())));
      return ret;
   }

   private static class AsyncTask implements Executor {
      @Override
      public void execute(Runnable command) {
         new Thread(command).start();
      }
   }
}
