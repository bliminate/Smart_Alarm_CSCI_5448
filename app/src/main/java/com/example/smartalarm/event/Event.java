package com.example.smartalarm.event;

import com.example.smartalarm.eventTimer.iEventTimer;

public class Event {
   public Event(){
      super();
   }

   public void setEventTimer(iEventTimer et){
      timer = et;
   }

   public iEventTimer getEventTimer(){
      return timer;
   }

   public void activateEvent(){
      //TODO: handle event activation
   }

   public void deactivateEvent(){
      //TODO: handle event deactivation
   }

   private iEventTimer timer;
}
