package com.example.smartalarm.database;

import androidx.room.Entity;

@Entity(primaryKeys = {"ImmediateEventID", "ActionID"})
public class ImmediateEventAction {
   private int ImmediateEventID;
   private int ActionID;

   public ImmediateEventAction(int EventID, int ActionID){
      this.ImmediateEventID = EventID;
      this.ActionID = ActionID;
   }
}
