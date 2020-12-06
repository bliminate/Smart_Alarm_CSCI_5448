package com.example.smartalarm.deviceAction;

import android.content.Context;
import android.media.MediaPlayer;

// Class implementation of strategy pattern
public class Sound implements iSound {
   private Sound(Context ctx){
      super();
      context = ctx;
      volume = 0;
   }

   public static Sound getInstance(Context ctx){
      if(instance == null){
         instance = new Sound(ctx);
      }
      return instance;
   }

   @Override
   public void playSound(Integer res) {
      MediaPlayer media = MediaPlayer.create(context, res);
      media.setVolume(volume,volume);
      media.start();
      media.release();
   }

   @Override
   public void setVolume(Integer vol) {
      //Volume code taken from:
      // https://stackoverflow.com/questions/5215459/android-mediaplayer-setvolume-function
      volume = (float) (Math.log(MAX_VOLUME - vol)/Math.log(MAX_VOLUME));
   }

   private float volume;
   private Context context;
   private final Integer MAX_VOLUME = 100;
   private static Sound instance;
}
