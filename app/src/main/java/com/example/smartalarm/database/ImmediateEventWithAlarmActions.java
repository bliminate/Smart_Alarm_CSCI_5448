package com.example.smartalarm.database;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;
import com.example.smartalarm.action.AlarmAction;
import com.example.smartalarm.event.ImmediateEvent;

import java.util.List;

public class ImmediateEventWithAlarmActions {
   @Embedded
   public ImmediateEvent event;

   @Relation(parentColumn = "ImmediateEventID",
             entityColumn = "ActionID",
             associateBy = @Junction(ImmediateEventAlarmAction.class))
   public List<AlarmAction> actions;

   public void subscribeActions(){
      for(AlarmAction a : actions){
         event.addObserver(a);
      }
   }
}
