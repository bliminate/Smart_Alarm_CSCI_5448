package com.example.smartalarm.eventTimer;

public class ImmediateTimer implements iEventTimer {

   @Override
   public void timeEvent() {
      //exit immediately
      return;
   }
}
