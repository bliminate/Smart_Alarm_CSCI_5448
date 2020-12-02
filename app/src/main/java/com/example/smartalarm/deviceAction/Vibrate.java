package com.example.smartalarm.deviceAction;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import androidx.annotation.RequiresApi;

import static android.content.Context.VIBRATOR_SERVICE;

public class Vibrate implements iVibrate{
   private Vibrate(Context ctx){
      super();
      context = ctx;
   }

   public static Vibrate getInstance(Context ctx){
      if(instance == null){
         instance = new Vibrate(ctx);
      }
      return instance;
   }

   @RequiresApi(api = Build.VERSION_CODES.O)
   @Override
   public void vibrate() {
      Vibrator vibrate = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
      vibrate.vibrate(VibrationEffect.createWaveform(new long[]{100,200,100}, new int[]{50, 250, 75}, 2));
   }

   private Context context;
   private static Vibrate instance;
}
