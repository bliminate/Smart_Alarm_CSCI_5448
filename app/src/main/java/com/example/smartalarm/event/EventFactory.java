package com.example.smartalarm.event;

import com.example.smartalarm.action.Action;

import java.util.Calendar;
import java.util.List;

// A simple factory to instantiate events
public class EventFactory {
   public static Event createEvent(String name, Calendar cal, Boolean isImmediate, List<Action> actions){
      Event event;
      if(isImmediate){
         event = new ImmediateEvent();
      }
      else{
         event = new DelayedEvent();
      }
      event.setName(name);
      event.setDelay(cal);

      for(Action a : actions){
         event.addObserver(a);
      }

      return event;
   }
}
