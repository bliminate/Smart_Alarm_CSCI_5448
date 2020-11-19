package com.example.smartalarm.deviceAction;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import androidx.annotation.RequiresApi;

import static android.content.Context.VIBRATOR_SERVICE;

public class Vibrate implements iVibrate{
   public Vibrate(Context ctx){
      super();
      context = ctx;
   }

   @RequiresApi(api = Build.VERSION_CODES.O)
   @Override
   public void vibrate() {
      Vibrator vibrate = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
      vibrate.vibrate(VibrationEffect.createWaveform(new long[]{100,200,100}, new int[]{500, 400, 500}, 2));
   }

   private Context context;
}
