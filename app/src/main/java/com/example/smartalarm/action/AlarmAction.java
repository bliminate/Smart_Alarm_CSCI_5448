package com.example.smartalarm.action;

import com.example.smartalarm.deviceAction.iSound;
import com.example.smartalarm.deviceAction.iVibrate;

import java.beans.PropertyChangeEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AlarmAction extends Action {
   public AlarmAction(iVibrate v, iSound s){
      super();
      vibrate = false;
      soundResource = 0;
      threads = null;
      vib = v;
      sound = s;
   }

   @Override
   public void executeAction() {
      if(threads == null) {
         threads = Executors.newFixedThreadPool(2);
         if (vibrate) {
            //vibrate phone
            threads.submit(new Runnable() {
               @Override
               public void run() {
                  vib.vibrate();
               }
            });
         }

         if (soundResource != 0) {
            //play sound in background thread
            threads.submit(new Runnable() {
               @Override
               public void run() {
                  sound.playSound(soundResource);
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
   private volatile iVibrate vib;
   private volatile iSound sound;
}
