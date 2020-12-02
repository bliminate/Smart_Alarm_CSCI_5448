package com.example.smartalarm.event;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.example.smartalarm.observer.iSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Calendar;

@Entity(tableName = "Event")
public abstract class Event implements iSubject {
   public Event(){
      super();
      subject = new PropertyChangeSupport(this);
   }

   public abstract void activateEvent();

   public abstract void deactivateEvent();

   public abstract void setDelay(Calendar c);

   public abstract Calendar getDelay();

   public void setName(String n){
      name = n;
   }

   public String getName(){
      return name;
   }

   public int getID(){ return ID; }

   @Override
   public void addObserver(PropertyChangeListener observer){
      subject.addPropertyChangeListener(observer);
   }

   @Override
   public void removeObserver(PropertyChangeListener observer){
      subject.removePropertyChangeListener(observer);
   }

   @Override
   public PropertyChangeListener[] getObservers(){
      return subject.getPropertyChangeListeners();
   }

   @Override
   public void notifyObservers(Object oldObj, Object newObj) {
      subject.firePropertyChange("action", oldObj, newObj);
   }

   @PrimaryKey(autoGenerate = true)
   private int ID;
   @Ignore
   private PropertyChangeSupport subject;
   @ColumnInfo(name = "Name")
   private String name;
   @ColumnInfo(name = "State")
   protected String currentState;
   @ColumnInfo(name = "Delay")
   protected Calendar delay;
}
