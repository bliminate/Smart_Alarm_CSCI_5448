package com.example.smartalarm.action;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.smartalarm.deviceAction.CoffeeMachine;

import java.beans.PropertyChangeEvent;
import java.io.Serializable;

@Entity(tableName="coffee_action")
public class CoffeeAction extends Action implements Serializable {
   public CoffeeAction(Integer ActionID, String name, Integer water, Integer ground){
      super();
      this.ActionID = ActionID;
      this.name = name;
      this.water = water;
      this.ground = ground;
   }

   public CoffeeAction(String url) {
      super();
      name = "";
      water = 0;
      ground = 0;
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
   public Integer getActionID(){
      return ActionID;
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

   public void setUrl(String url) {
      this.url = url;
   }

   public String getUrl(){
      return url;
   }

   public void setWater(Integer water){
      this.water = water;
   }

   public Integer getWater(){
      return water;
   }

   public void setGround(Integer ground){
      this.ground = ground;
   }

   public Integer getGround(){
      return ground;
   }

   @PrimaryKey(autoGenerate = true)
   @ColumnInfo(name = "ActionID")
   private Integer ActionID;

   @ColumnInfo(name = "Name")
   private String name;

   @ColumnInfo(name = "Water")
   private Integer water;

   @ColumnInfo(name = "Ground")
   private Integer ground;

   @Ignore
   private String url;
}
