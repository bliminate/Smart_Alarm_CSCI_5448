package com.example.smartalarm.deviceAction;

import android.content.Context;
import android.media.MediaPlayer;

public class Sound implements iSound {
   Sound(Context ctx){
      super();
      context = ctx;
   }

   @Override
   public void playSound(Integer res) {
      MediaPlayer media = MediaPlayer.create(context, res);
      media.start();
      media.release();
   }

   private Context context;
}
