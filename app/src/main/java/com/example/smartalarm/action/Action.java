package com.example.smartalarm.action;

import java.beans.PropertyChangeListener;

//Abstract Base class for Action implements the PropertyChangeListener
// interface so that actions can observe events.
//Actions are also a Model in the MVC pattern
public abstract class Action implements PropertyChangeListener {
   public abstract void executeAction();

   public abstract void stopAction();

   public abstract void setName(String s);

   public abstract String getName();

    public abstract Integer getActionID();
}
