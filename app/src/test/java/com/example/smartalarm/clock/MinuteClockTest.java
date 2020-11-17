package com.example.smartalarm.clock;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class MinuteClockTest {

   @Test
   void testGetInstance() {
      MinuteClock mClock = MinuteClock.getInstance();
      assertNotEquals(null, mClock);
   }

   // This test takes 1 minute to run
   @Test
   void testConcurrency() throws InterruptedException {
      MinuteClock mClock = MinuteClock.getInstance();
      testClockObserver tco = new testClockObserver();
      mClock.addObserver(tco);
      Calendar startTime = Calendar.getInstance();
      mClock.startClock();
      Thread.sleep(60000);
      mClock.stopClock();
      assertTrue(tco.observedTime.after(startTime));
   }

}