package com.example.smartalarm.database;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;
import com.example.smartalarm.action.Action;
import com.example.smartalarm.event.DelayedEvent;

import java.util.List;

public class DelayedEventWithActions {
   @Embedded
   public DelayedEvent event;

   @Relation(parentColumn = "eID",
         entityColumn = "aID",
         associateBy = @Junction(DelayedEventAction.class))
   public List<Action> actions;
}
