package com.example.smartalarm.action;

import com.example.smartalarm.deviceAction.iVibrate;

public class testVibrate implements iVibrate {
   testVibrate(){
      vibCalled = false;
   }

   @Override
   public void vibrate() {
      vibCalled = true;
   }

   public volatile Boolean vibCalled;
}
