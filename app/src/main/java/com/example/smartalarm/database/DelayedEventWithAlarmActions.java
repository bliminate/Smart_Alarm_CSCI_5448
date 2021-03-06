package com.example.smartalarm.database;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;
import com.example.smartalarm.action.AlarmAction;
import com.example.smartalarm.clock.MinuteClock;
import com.example.smartalarm.event.DelayedEvent;

import java.util.List;

public class DelayedEventWithAlarmActions {
   @Embedded
   public DelayedEvent event;

   @Relation(parentColumn = "DelayedEventID",
         entityColumn = "ActionID",
         associateBy = @Junction(DelayedEventAlarmAction.class))
   public List<AlarmAction> actions;

   public void subscribeActions(){
      //Have event subscribe to clock
      MinuteClock.getInstance().addObserver(event);
      //Have actions subscribe to the event
      for(AlarmAction a : actions){
         event.addObserver(a);
      }
   }
}
