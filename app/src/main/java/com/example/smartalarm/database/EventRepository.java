package com.example.smartalarm.database;

import android.app.Application;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import com.example.smartalarm.action.Action;
import com.example.smartalarm.action.AlarmAction;
import com.example.smartalarm.action.CoffeeAction;
import com.example.smartalarm.dao.*;
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
   private final DelayedEventWithAlarmActionsDao DEWAAD;
   private final ImmediateEventWithAlarmActionsDao IEWAAD;
   private final DelayedEventWithCoffeeActionsDao DEWCAD;
   private final ImmediateEventWithCoffeeActionsDao IEWCAD;
   private final LiveData<List<Event>> mEvents;

   @RequiresApi(api = Build.VERSION_CODES.N)
   public EventRepository(Application app){
      SmartAlarmDatabase db = SmartAlarmDatabase.getDatabase(app);
      DED = db.delayedEventDao();
      IED = db.immediateEventDao();
      DEWAAD = db.delayedEventWithAlarmActionsDao();
      IEWAAD = db.immediateEventWithAlarmActionsDao();
      DEWCAD = db.delayedEventWithCoffeeActionsDao();
      IEWCAD = db.immediateEventWithCoffeeActionsDao();

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
         if(a instanceof AlarmAction) {
            ImmediateEventAlarmAction iea = new ImmediateEventAlarmAction(event.getID(), a.getActionID());
            IEWAAD.insert(iea);
         }
         else if(a instanceof CoffeeAction){
            ImmediateEventCoffeeAction iea = new ImmediateEventCoffeeAction(event.getID(), a.getActionID());
            IEWCAD.insert(iea);
         }
      }
   }

   private void insertDelayedEventAction(Event event){
      for (PropertyChangeListener l :  event.getObservers()) {
         Action a = (Action) l;
         if(a instanceof AlarmAction) {
            DelayedEventAlarmAction dea = new DelayedEventAlarmAction(event.getID(), a.getActionID());
            DEWAAD.insert(dea);
         }
         else if(a instanceof CoffeeAction){
            DelayedEventCoffeeAction dea = new DelayedEventCoffeeAction(event.getID(), a.getActionID());
            DEWCAD.insert(dea);
         }
      }
   }

   //Mediator Live data:
   // https://developer.android.com/reference/androidx/lifecycle/MediatorLiveData
   @RequiresApi(api = Build.VERSION_CODES.N)
   private LiveData<List<Event>> getAllEvents(){
      MediatorLiveData<List<Event>> ret = new MediatorLiveData<>();
      LiveData<List<ImmediateEventWithAlarmActions>> immediateAlarm = IEWAAD.getImmediateEventWithAlarmActions();
      LiveData<List<DelayedEventWithAlarmActions>> delayedAlarm = DEWAAD.getDelayedEventWithAlarmActions();
      LiveData<List<ImmediateEventWithCoffeeActions>> immediateCoffee = IEWCAD.getImmediateEventWithCoffeeActions();
      LiveData<List<DelayedEventWithCoffeeActions>> delayedCoffee = DEWCAD.getDelayedEventWithCoffeeActions();
      //Have all actions subscribe to their respective event
      for (ImmediateEventWithAlarmActions i : immediateAlarm.getValue()) {
          i.subscribeActions();
      }
      for (DelayedEventWithAlarmActions d : delayedAlarm.getValue()) {
          d.subscribeActions();
      }
      for (ImmediateEventWithCoffeeActions i : immediateCoffee.getValue()) {
         i.subscribeActions();
      }
      for (DelayedEventWithCoffeeActions d : delayedCoffee.getValue()) {
         d.subscribeActions();
      }

      ret.addSource(immediateAlarm, value -> ret.setValue(value.stream().map(x -> (Event)x.event).collect(Collectors.toList())));
      ret.addSource(delayedAlarm, value -> ret.setValue(value.stream().map(x -> (Event)x.event).collect(Collectors.toList())));
      ret.addSource(immediateCoffee, value -> ret.setValue(value.stream().map(x -> (Event)x.event).collect(Collectors.toList())));
      ret.addSource(delayedCoffee, value -> ret.setValue(value.stream().map(x -> (Event)x.event).collect(Collectors.toList())));
      return ret;
   }

   private static class AsyncTask implements Executor {
      @Override
      public void execute(Runnable command) {
         new Thread(command).start();
      }
   }
}
