package com.example.smartalarm.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"ImmediateEventID", "ActionID"})
public class ImmediateEventAlarmAction {
   @NonNull
   public Integer ImmediateEventID;
   @NonNull
   public Integer ActionID;

   public ImmediateEventAlarmAction(Integer ImmediateEventID, Integer ActionID){
      this.ImmediateEventID = ImmediateEventID;
      this.ActionID = ActionID;
   }
}
