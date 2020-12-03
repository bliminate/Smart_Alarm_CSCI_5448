package com.example.smartalarm.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.smartalarm.action.AlarmAction;
import com.example.smartalarm.dataStructures.NameIdPair;
import com.example.smartalarm.event.ImmediateEvent;

import java.util.List;

@Dao
public interface ImmediateEventDao {
   @Insert
   void insert(ImmediateEvent event);

   @Update
   void updateAlarm(ImmediateEvent event);

   @Query("SELECT Name, id FROM immediate_event")
   LiveData<List<NameIdPair>> getAllNames();

   @Query("SELECT * FROM immediate_event WHERE id = :ID")
   AlarmAction getImmediateEvent(int ID);
}
