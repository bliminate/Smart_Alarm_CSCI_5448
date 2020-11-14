package com.example.smartalarm.event;

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

   private String currentState;
}
