package com.example.smartalarm.event;

import com.example.smartalarm.observer.iSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Event implements iSubject {
   public Event(){
      super();
      subject = new PropertyChangeSupport(this);
   }

   public abstract void activateEvent();

   public abstract void deactivateEvent();

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
}
