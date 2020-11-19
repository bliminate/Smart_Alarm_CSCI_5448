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

   @Test
   void testExecuteAlarmActionOnlyVibrate() throws InterruptedException {
      testVibrate v = new testVibrate();
      testSound s = new testSound();
      AlarmAction aa = new AlarmAction(v, s);
      aa.toggleVibrate();
      aa.executeAction();
      sleep(1000);
      aa.stopAction();
      assertTrue(v.vibCalled, "Vibrate was not called");
      assertFalse(s.calledSound, "Sound was called when it shouldn't have been");
   }

   @Test
   void testExecuteAlarmActionOnlySound() throws InterruptedException {
      testVibrate v = new testVibrate();
      testSound s = new testSound();
      AlarmAction aa = new AlarmAction(v, s);
      aa.setSoundResource(1);
      aa.executeAction();
      sleep(1000);
      aa.stopAction();
      assertFalse(v.vibCalled, "Vibrate was called");
      assertTrue(s.calledSound, "Sound was not called");
   }

   @Test
   void testExecuteAlarmActionToggleOnOffOnVibrate() throws InterruptedException {
      testVibrate v = new testVibrate();
      testSound s = new testSound();
      AlarmAction aa = new AlarmAction(v, s);
      aa.toggleVibrate();
      aa.toggleVibrate();
      aa.toggleVibrate();
      aa.executeAction();
      sleep(1000);
      aa.stopAction();
      assertTrue(v.vibCalled, "Vibrate was not called");
      assertFalse(s.calledSound, "Sound was called when it shouldn't have been");
   }
}