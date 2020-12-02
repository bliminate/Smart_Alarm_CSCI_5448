package com.example.smartalarm.action;

import com.example.smartalarm.deviceAction.CoffeeMachine;

import java.beans.PropertyChangeEvent;
import java.io.Serializable;

public class CoffeeAction extends Action implements Serializable {
   public CoffeeAction(){
      super();
      coffeeMachine = new CoffeeMachine();
   }

   @Override
   public void executeAction() {
      CoffeeMachine coffeeMachine = new CoffeeMachine(water, ground, url);
      coffeeMachine.execute();
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

   public CoffeeMachine getCoffeeMachine() {
      return coffeeMachine;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public String getUrl(){
      return url;
   }


   public void setWater(int water){
      this.water = water;
   }

   public int getWater(){
      return water;
   }

   public void setGround(int ground){
      this.ground = ground;
   }

   public int getGround(){
      return ground;
   }

   private int water;
   private int ground;
   private String url;
   private String name;
   private CoffeeMachine coffeeMachine;
}
