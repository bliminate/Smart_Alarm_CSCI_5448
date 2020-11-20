package com.example.smartalarm.action;

import com.example.smartalarm.deviceAction.iSound;

public class testSound implements iSound {
   testSound(){
      super();
      calledSound = false;
   }
   @Override
   public void playSound(Integer res) {
      calledSound = true;
   }

   public Boolean calledSound;
}
