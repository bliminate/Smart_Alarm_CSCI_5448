package com.example.smartalarm.database;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;
import com.example.smartalarm.action.CoffeeAction;
import com.example.smartalarm.event.ImmediateEvent;

import java.util.List;

public class ImmediateEventWithCoffeeActions {
   @Embedded
   public ImmediateEvent event;

   @Relation(parentColumn = "ImmediateEventID",
             entityColumn = "ActionID",
             associateBy = @Junction(ImmediateEventCoffeeAction.class))
   public List<CoffeeAction> actions;

   public void subscribeActions(){
      for(CoffeeAction a : actions){
         event.addObserver(a);
      }
   }
}
