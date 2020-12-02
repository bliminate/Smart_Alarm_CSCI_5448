package com.example.smartalarm.event;

import java.util.Calendar;

public class ImmediateEvent extends Event {
   ImmediateEvent(){
      super();
      currentState = "deactivated";
   }

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
   public void setDelay(Calendar c) {
      delay = Calendar.getInstance();
   }

   @Override
   public Calendar getDelay() {
      return delay;
   }
}
