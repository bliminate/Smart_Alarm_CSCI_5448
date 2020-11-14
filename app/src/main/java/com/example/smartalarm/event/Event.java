package com.example.smartalarm.event;

import com.example.smartalarm.eventTimer.iEventTimer;

public abstract class Event {
   public Event(){
      super();
   }

   public abstract void activateEvent();

   public abstract void deactivateEvent();

   public void addAction(){
      
   }
}
