package com.example.smartalarm.database;

import androidx.room.Entity;

@Entity(primaryKeys = {"EventID", "ActionID"})
public class EventWithActions {
   private int EventID;
   private int ActionID;

   public EventWithActions(int EventID, int ActionID){
      this.EventID = EventID;
      this.ActionID = ActionID;
   }
}
