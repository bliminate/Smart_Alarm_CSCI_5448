package com.example.smartalarm.deviceAction;

import android.media.AudioManager;
import com.example.smartalarm.R;
import org.junit.Test;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;
import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class SoundTest {

   @Test
   public void playSound() throws InterruptedException {
      Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
      Sound s = new Sound(appContext);
      s.playSound(R.raw.alarm_gentle);
      sleep(1000);
      AudioManager manager = (AudioManager)appContext.getSystemService(Context.AUDIO_SERVICE);
      Boolean active = manager.isMusicActive();
      assertTrue(active);
   }
}