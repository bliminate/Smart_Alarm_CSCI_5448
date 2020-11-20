package com.example.smartalarm.action;

import java.beans.PropertyChangeListener;

public abstract class Action implements PropertyChangeListener {
   public abstract void executeAction();

   public abstract void stopAction();

   public abstract void setName(String s);

   public abstract String getName();
}
