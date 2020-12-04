package com.example.smartalarm.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.smartalarm.event.DelayedEvent;

import java.util.List;

@Dao
public interface DelayedEventDao {
   @Insert
   void insert(DelayedEvent event);

   @Update
   void update(DelayedEvent event);

   @Query("SELECT * FROM delayed_event")
   LiveData<List<DelayedEvent>> getAllEvents();

   @Query("SELECT * FROM delayed_event WHERE DelayedEventID = :ID")
   DelayedEvent getDelayedEvent(int ID);
}
