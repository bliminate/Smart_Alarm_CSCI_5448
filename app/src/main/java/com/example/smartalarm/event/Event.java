package com.example.smartalarm.event;

import androidx.room.Ignore;
import com.example.smartalarm.observer.iSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Calendar;

public abstract class Event implements iSubject, Serializable {
   public Event(){
      super();
      subject = new PropertyChangeSupport(this);
   }

   public abstract void activateEvent();

   public abstract void deactivateEvent();

   public abstract String getCurrentState();

   public abstract void setDelay(Calendar c);

   public abstract Calendar getDelay();

   public abstract void setName(String n);

   public abstract String getName();

   public abstract int getID();

   public abstract void setCurrentState(String s);

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

   @Ignore
   private PropertyChangeSupport subject;

}
