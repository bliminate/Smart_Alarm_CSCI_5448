package com.example.smartalarm.deviceAction;

import android.content.Context;
import android.media.AudioManager;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.example.smartalarm.R;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class SoundTest {

   @Test
   public void playSound() throws InterruptedException {
      Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
      Sound s = Sound.getInstance(appContext);
      s.playSound(R.raw.alarm_gentle);
      sleep(1000);
      AudioManager manager = (AudioManager)appContext.getSystemService(Context.AUDIO_SERVICE);
      Boolean active = manager.isMusicActive();
      assertTrue(active);
   }
}