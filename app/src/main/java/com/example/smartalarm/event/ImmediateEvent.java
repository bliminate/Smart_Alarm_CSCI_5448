package com.example.smartalarm.event;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity(tableName = "immediate_event")
public class ImmediateEvent extends Event {
   ImmediateEvent(){
      super();
      currentState = "deactivated";
   }

   @Override
   public void setName(String n){ name = n; }

   @Override
   public String getName(){ return name; }

   @Override
   public int getID(){ return ID; }

   @Override
   public void activateEvent() {
      String oldState = currentState;
      currentState = "activated";
      notifyObservers(oldState, currentState);
   }

   @Override
   public void deactivateEvent() {
      String oldState = currentState;
      currentState = "deactivated";
      notifyObservers(oldState, currentState);
   }

   @Override
   public void setDelay(Calendar c) {
      delay = Calendar.getInstance();
   }

   @Override
   public Calendar getDelay() {
      return delay;
   }

   @PrimaryKey(autoGenerate = true)
   private int ID;
   @ColumnInfo(name = "Name")
   private String name;
   @ColumnInfo(name = "State")
   private String currentState;
   @ColumnInfo(name = "Delay")
   private Calendar delay;
}
