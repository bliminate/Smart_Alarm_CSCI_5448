package com.example.smartalarm.database;

import androidx.room.Entity;

@Entity(primaryKeys = {"DelayedEventID", "ActionID"})
public class DelayedEventAction {
   public int DelayedEventID;
   public int ActionID;

   public DelayedEventAction(int DelayedEventID, int ActionID){
      this.DelayedEventID = DelayedEventID;
      this.ActionID = ActionID;
   }
}
