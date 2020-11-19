package com.example.smartalarm.action;

import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

class AlarmActionTest {

   @Test
   void testExecuteAlarmAction() throws InterruptedException {
      testVibrate v = new testVibrate();
      testSound s = new testSound();
      AlarmAction aa = new AlarmAction(v, s);
      aa.toggleVibrate();
      aa.setSoundResource(1);
      aa.executeAction();
      sleep(1000);
      aa.stopAction();
      assertTrue(v.vibCalled, "Vibrate was not called");
      assertTrue(s.calledSound, "Sound was not called");
   }
}