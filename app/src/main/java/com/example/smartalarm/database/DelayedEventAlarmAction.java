package com.example.smartalarm.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"DelayedEventID", "ActionID"})
public class DelayedEventAlarmAction {
   @NonNull
   public Integer DelayedEventID;
   @NonNull
   public Integer ActionID;

   public DelayedEventAlarmAction(Integer DelayedEventID, Integer ActionID){
      this.DelayedEventID = DelayedEventID;
      this.ActionID = ActionID;
   }
}
