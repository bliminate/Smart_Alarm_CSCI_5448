package com.example.smartalarm.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.smartalarm.action.AlarmAction;
import com.example.smartalarm.dataStructures.NameIdPair;
import com.example.smartalarm.event.DelayedEvent;

import java.util.List;

@Dao
public interface DelayedEventDao {
   @Insert
   void insert(DelayedEvent event);

   @Update
   void updateAlarm(DelayedEvent event);

   @Query("SELECT Name, id FROM delayed_event")
   LiveData<List<NameIdPair>> getAllNames();

   @Query("SELECT * FROM delayed_event WHERE id = :ID")
   AlarmAction getDelayedEvent(int ID);
}
