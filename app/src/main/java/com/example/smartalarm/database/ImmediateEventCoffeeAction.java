package com.example.smartalarm.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"ImmediateEventID", "ActionID"})
public class ImmediateEventCoffeeAction {
   @NonNull
   public Integer ImmediateEventID;
   @NonNull
   public Integer ActionID;

   public ImmediateEventCoffeeAction(Integer ImmediateEventID, Integer ActionID){
      this.ImmediateEventID = ImmediateEventID;
      this.ActionID = ActionID;
   }
}
