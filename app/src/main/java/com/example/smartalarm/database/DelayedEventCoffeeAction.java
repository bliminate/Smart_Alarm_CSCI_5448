package com.example.smartalarm.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"DelayedEventID", "ActionID"})
public class DelayedEventCoffeeAction {
   @NonNull
   public Integer DelayedEventID;
   @NonNull
   public Integer ActionID;

   public DelayedEventCoffeeAction(Integer DelayedEventID, Integer ActionID){
      this.DelayedEventID = DelayedEventID;
      this.ActionID = ActionID;
   }
}
