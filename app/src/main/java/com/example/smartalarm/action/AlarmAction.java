package com.example.smartalarm.action;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import com.example.smartalarm.R;

import java.beans.PropertyChangeEvent;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static android.content.Context.VIBRATOR_SERVICE;

public class AlarmAction extends Action {
   public AlarmAction(Context ctx){
      super();
      vibrate = false;
      soundResource = 0;
      threads = null;
      context = ctx;
   }

   @Override
   public void executeAction() {
      if(threads == null) {
         threads = Executors.newFixedThreadPool(2);
         if (vibrate) {
            //vibrate phone
            threads.submit(new Runnable() {
               @RequiresApi(api = Build.VERSION_CODES.O)
               @Override
               public void run() {
                  Vibrator vibrate = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
                  vibrate.vibrate(VibrationEffect.createWaveform(new long[]{100,200,100}, new int[]{500, 400, 500}, 2));
               }
            });
         }

         if (soundResource != 0) {
            //play sound in background thread
            threads.submit(new Runnable() {
               @Override
               public void run() {
                  MediaPlayer media = MediaPlayer.create(context, soundResource);
                  media.start();
               }
            });
         }
      }
   }

   @Override
   public void stopAction() {
      if(threads != null){
         threads.shutdownNow();
         threads = null;
      }
   }

   @Override
   public void setName(String s) {
      name = s;
   }

   @Override
   public String getName() {
      return name;
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt) {
      if(evt.getPropertyName() == "action"){
         executeAction();
      }
   }

   public void toggleVibrate(){
      vibrate = !vibrate;
   }

   public Boolean getVibrate(){
      return vibrate;
   }

   public void setSoundResource(Integer r){
      soundResource = r;
   }

   public Integer getSoundResource(){
      return soundResource;
   }

   private String name;
   private Boolean vibrate;
   private Integer soundResource;
   private ExecutorService threads;
   private Context context;
}
