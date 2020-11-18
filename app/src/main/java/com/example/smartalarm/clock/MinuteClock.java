package com.example.smartalarm.clock;

import com.example.smartalarm.observer.iSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MinuteClock implements iSubject {
   private MinuteClock(){
      super();
      time = Calendar.getInstance();
      subject = new PropertyChangeSupport(this);
   }

   public static MinuteClock getInstance(){
      if(minuteClock == null){
         minuteClock = new MinuteClock();
      }

      return minuteClock;
   }

   public void startClock(){
      if(thread == null){
         thread = Executors.newScheduledThreadPool(1);
         thread.scheduleAtFixedRate(
            new Runnable() {
               public void run() {
                  tick();
               }
            }, 0,1, TimeUnit.MINUTES );
      }
   }

   public void stopClock(){
      if(thread != null) {
         thread.shutdown();
         thread = null;
      }
   }

   private void tick(){
      Calendar oldTime = time;
      time = Calendar.getInstance();
      notifyObservers(oldTime, time);
   }

   @Override
   public void addObserver(PropertyChangeListener o) {
      subject.addPropertyChangeListener(o);
   }

   @Override
   public void removeObserver(PropertyChangeListener o) {
      subject.removePropertyChangeListener(o);
   }

   @Override
   public PropertyChangeListener[] getObservers() {
      return subject.getPropertyChangeListeners();
   }

   @Override
   public void notifyObservers(Object oldObj, Object newObj) {
      subject.firePropertyChange("clock", oldObj, newObj);
   }

   private Calendar time;
   private static MinuteClock minuteClock;
   private PropertyChangeSupport subject;
   // Threading code referenced:
   // https://stackoverflow.com/questions/18365795/best-way-to-create-a-background-thread-in-java
   private ScheduledExecutorService thread;
   private ScheduledFuture threadHandle;
}
