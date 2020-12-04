package com.example.smartalarm.database;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;
import com.example.smartalarm.action.Action;
import com.example.smartalarm.event.ImmediateEvent;

import java.util.List;

public class ImmediateEventWithActions {
   @Embedded
   public ImmediateEvent event;

   @Relation(parentColumn = "eID",
             entityColumn = "aID",
             associateBy = @Junction(ImmediateEventAction.class))
   public List<Action> actions;

   public void subscribeActions(){
      for(Action a : actions){
         event.addObserver(a);
      }
   }
}
