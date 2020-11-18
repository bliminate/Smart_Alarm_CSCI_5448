package com.example.smartalarm.action;

import java.beans.PropertyChangeEvent;

public class AlarmAction extends Action {
   public AlarmAction(){
      super();
      vibrate = false;
      soundResource = 0;
   }

   @Override
   public void executeAction() {

   }

   @Override
   public void stopAction() {

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
}
