package com.example.smartalarm.database;

import android.app.Application;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import com.example.smartalarm.action.Action;
import com.example.smartalarm.dao.DelayedEventDao;
import com.example.smartalarm.dao.DelayedEventWithActionsDao;
import com.example.smartalarm.dao.ImmediateEventDao;
import com.example.smartalarm.dao.ImmediateEventWithActionsDao;
import com.example.smartalarm.event.DelayedEvent;
import com.example.smartalarm.event.Event;
import com.example.smartalarm.event.ImmediateEvent;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

public class EventRepository {
   private final DelayedEventDao DED;
   private final ImmediateEventDao IED;
   private final DelayedEventWithActionsDao DEWAD;
   private final ImmediateEventWithActionsDao IEWAD;
   private final LiveData<List<Event>> mEvents;

   @RequiresApi(api = Build.VERSION_CODES.N)
   public EventRepository(Application app){
      SmartAlarmDatabase db = SmartAlarmDatabase.getDatabase(app);
      DED = db.delayedEventDao();
      IED = db.immediateEventDao();
      DEWAD = db.delayedEventWithActionsDao();
      IEWAD = db.immediateEventWithActionsDao();
      mEvents = getAllEvents();
   }

   public LiveData<List<Event>> getEvents(){
      return mEvents;
   }

   public void insert(Event event){
      if(event instanceof ImmediateEvent) {
         new EventRepository.AsyncTask().execute(() -> IED.insert((ImmediateEvent) event));
         insertImmediateEventAction(event);
      }
      else if(event instanceof DelayedEvent){
         new EventRepository.AsyncTask().execute(() -> DED.insert((DelayedEvent) event));
         insertDelayedEventAction(event);
      }
   }

   public void update(Event event){
      if(event instanceof ImmediateEvent) {
         new EventRepository.AsyncTask().execute(() -> IED.update((ImmediateEvent) event));
         insertImmediateEventAction(event);
      }
      else if(event instanceof DelayedEvent){
         new EventRepository.AsyncTask().execute(() -> DED.update((DelayedEvent) event));
         insertDelayedEventAction(event);
      }
   }

   private void insertImmediateEventAction(Event event){
      for (PropertyChangeListener l :  event.getObservers()) {
         Action a = (Action) l;
         ImmediateEventAlarmAction iea = new ImmediateEventAlarmAction(event.getID(), a.getID());
         IEWAD.insert(iea);
      }
   }

   private void insertDelayedEventAction(Event event){
      for (PropertyChangeListener l :  event.getObservers()) {
         Action a = (Action) l;
         DelayedEventAction dea = new DelayedEventAction(event.getID(), a.getID());
         DEWAD.insert(dea);
      }
   }

   //Mediator Live data:
   // https://developer.android.com/reference/androidx/lifecycle/MediatorLiveData
   @RequiresApi(api = Build.VERSION_CODES.N)
   private LiveData<List<Event>> getAllEvents(){
      MediatorLiveData<List<Event>> ret = new MediatorLiveData<>();
      LiveData<List<ImmediateEventWithActions>> immediate = IEWAD.getImmediateEventWithActions();
      LiveData<List<DelayedEventWithActions>> delayed = DEWAD.getDelayedEventWithActions();
      //Have all actions subscribe to their respective event
      for (ImmediateEventWithActions i : immediate.getValue()) {
          i.subscribeActions();
      }
      for (DelayedEventWithActions d : delayed.getValue()) {
          d.subscribeActions();
      }

      ret.addSource(immediate, value -> ret.setValue(value.stream().map(x -> (Event)x.event).collect(Collectors.toList())));
      ret.addSource(delayed, value -> ret.setValue(value.stream().map(x -> (Event)x.event).collect(Collectors.toList())));
      return ret;
   }

   private static class AsyncTask implements Executor {
      @Override
      public void execute(Runnable command) {
         new Thread(command).start();
      }
   }
}
