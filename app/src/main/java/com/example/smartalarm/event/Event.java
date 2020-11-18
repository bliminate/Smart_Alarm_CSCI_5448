package com.example.smartalarm.event;

import com.example.smartalarm.observer.iSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Calendar;

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

   private PropertyChangeSupport subject;
   private String name;
   private String currentState;
}
