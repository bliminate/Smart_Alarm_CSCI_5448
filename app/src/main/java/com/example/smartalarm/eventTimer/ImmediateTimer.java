package com.example.smartalarm.eventTimer;

public class ImmediateTimer implements EventTimer {

   @Override
   public void timeEvent() {
      //exit immediately
      return;
   }
}
