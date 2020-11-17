package com.example.smartalarm.clock;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

public class testClockObserver implements PropertyChangeListener {
   public testClockObserver(){
      super();
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt) {
      observedTime = (Calendar) evt.getNewValue();
   }

   public Calendar observedTime;
}
