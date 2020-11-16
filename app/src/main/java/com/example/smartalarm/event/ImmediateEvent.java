package com.example.smartalarm.event;

import java.util.Calendar;

public class ImmediateEvent extends Event {
   ImmediateEvent(){
      super();
      currentState = "waiting";
   }

   @Override
   public void activateEvent() {
      String oldState = currentState;
      currentState = "active";
      notifyObservers(oldState, currentState);
   }

   @Override
   public void deactivateEvent() {
      String oldState = currentState;
      currentState = "waiting";
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

   private String currentState;
   private Calendar delay;

}
