package com.example.smartalarm.observer;

import java.beans.PropertyChangeListener;

// Interface to implement to make objects observable
// Interface referenced:
// https://learning.oreilly.com/library/view/head-first-design/0596007124/ch02.html
public interface iSubject {
   void addObserver(PropertyChangeListener o);

   void removeObserver(PropertyChangeListener o);

   PropertyChangeListener[] getObservers();

   void notifyObservers(Object oldObj, Object newObj);
}