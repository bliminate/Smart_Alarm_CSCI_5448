package com.example.smartalarm.database;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import com.example.smartalarm.dao.DelayedEventDao;
import com.example.smartalarm.dao.ImmediateEventDao;
import com.example.smartalarm.event.DelayedEvent;
import com.example.smartalarm.event.Event;
import com.example.smartalarm.event.ImmediateEvent;

import java.util.List;
import java.util.concurrent.Executor;

public class EventRepository {
   private DelayedEventDao DED;
   private ImmediateEventDao IED;
   private LiveData<List<Event>> mEvents;

   public EventRepository(Application app){
      SmartAlarmDatabase db = SmartAlarmDatabase.getDatabase(app);
      DED = db.delayedEventDao();
      IED = db.immediateEventDao();
      mEvents = getAllEvents();
   }

   public LiveData<List<Event>> getAlarmNames(){
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
   private LiveData<List<Event>> getAllEvents(){
      MediatorLiveData<List<Event>> ret = new MediatorLiveData<>();
      LiveData<List<Event>> immediate = IED.getAllEvents();
      LiveData<List<Event>> delayed = DED.getAllEvents();
      ret.addSource(immediate, value -> ret.setValue(value));
      ret.addSource(delayed, value -> ret.setValue(value));
      return ret;
   }

   private static class AsyncTask implements Executor {
      @Override
      public void execute(Runnable command) {
         new Thread(command).start();
      }
   }
}
