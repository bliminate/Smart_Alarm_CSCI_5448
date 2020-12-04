package com.example.smartalarm.database;

import androidx.room.Entity;

@Entity(primaryKeys = {"ImmediateEventID", "AlarmActionID"})
public class ImmediateEventAlarmAction {
   public int ImmediateEventID;
   public int AlarmActionID;

   public ImmediateEventAlarmAction(int ImmediateEventID, int ActionID){
      this.ImmediateEventID = ImmediateEventID;
      this.AlarmActionID = ActionID;
   }
}
