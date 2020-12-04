package com.example.smartalarm.database;

import androidx.room.Entity;

@Entity(primaryKeys = {"DelayedEventID", "ActionID"})
public class DelayedEventAction {
   private int DelayedEventID;
   private int ActionID;

   public DelayedEventAction(int EventID, int ActionID){
      this.DelayedEventID = EventID;
      this.ActionID = ActionID;
   }
}
