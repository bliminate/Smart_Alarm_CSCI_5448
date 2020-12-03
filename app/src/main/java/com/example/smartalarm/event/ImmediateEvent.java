package com.example.smartalarm.event;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.example.smartalarm.database.CalendarTypeConverter;

import java.util.Calendar;

@Entity(tableName = "immediate_event")
public class ImmediateEvent extends Event {
   public ImmediateEvent(){
      super();
      currentState = "deactivated";
   }

   @Override
   public void setName(String n){ name = n; }

   @Override
   public String getName(){ return name; }

   @Override
   public int getID(){ return ID; }

   public void setID(int id){ ID = id; }

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
   public String getCurrentState(){ return currentState; };

   @Override
   public void setDelay(Calendar c) {
      delay = Calendar.getInstance();
   }

   @Override
   public Calendar getDelay() {
      return delay;
   }

   @Override
   public String getCurrentState(){ return currentState; }

   @Override
   public void setCurrentState(String s) {
      currentState = s;
   }

   @PrimaryKey(autoGenerate = true)
   private int ID;
   @ColumnInfo(name = "Name")
   private String name;
   @ColumnInfo(name = "State")
   private String currentState;
   @ColumnInfo(name = "Delay")
   @TypeConverters({CalendarTypeConverter.class})
   private Calendar delay;
}
