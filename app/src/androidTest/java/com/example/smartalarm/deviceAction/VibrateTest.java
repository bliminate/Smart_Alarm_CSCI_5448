package com.example.smartalarm.deviceAction;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class VibrateTest {

   @Test
   public void testVibrateMethod() {
      // Context of the app under test.
      Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
      Vibrate v = Vibrate.getInstance(appContext);
      v.vibrate();
      assertEquals("com.example.smartalarm", appContext.getPackageName());
   }
}