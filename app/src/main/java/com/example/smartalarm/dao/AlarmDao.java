package com.example.smartalarm.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.smartalarm.action.AlarmAction;
import com.example.smartalarm.dataStructures.NameIdPair;

import java.util.List;

@Dao
public interface AlarmDao {
   @Insert
   void insert(AlarmAction alarm);

   @Update
   void updateAlarm(AlarmAction alarm);

   @Query("SELECT Name, id FROM alarm_action")
   List<NameIdPair> getAllNames();

   @Query("SELECT * FROM alarm_action WHERE id = :ID")
   AlarmAction getAlarmAction(int ID);
}