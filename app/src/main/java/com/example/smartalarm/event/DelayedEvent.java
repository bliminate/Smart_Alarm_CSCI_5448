package com.example.smartalarm.event;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

@Entity(tableName = "delayed_event")
public class DelayedEvent extends Event implements PropertyChangeListener {
   DelayedEvent(){
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
   }

   @Override
   public void deactivateEvent() {
      String oldState = currentState;
      currentState = "deactivated";
      notifyObservers(oldState, currentState);
   }

   @Override
   public void setDelay(Calendar c) {
      delay = c;
   }

   @Override
   public Calendar getDelay() {
      return delay;
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt) {
      if(evt.getPropertyName().contains("clock")){
         if(currentState.equals("activated")){
            //TODO: Implement clock
            checkDelay((Calendar) evt.getNewValue());
         }
      }
   }

   protected void checkDelay(Calendar currentTime){
      if(currentTime.after(delay)){
         String oldState = currentState;
         currentState = "occurring";
         notifyObservers(oldState, currentState);
      }
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
