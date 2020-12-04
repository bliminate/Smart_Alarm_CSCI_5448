package com.example.smartalarm.dao;

import androidx.lifecycle.LiveData;
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
   void update(AlarmAction alarm);

   @Query("SELECT Name, ActionID FROM alarm_action")
   LiveData<List<NameIdPair>> getAllNames();

   @Query("SELECT * FROM alarm_action")
   LiveData<List<AlarmAction>> getAllActions();

   @Query("SELECT * FROM alarm_action WHERE ActionID = :ID")
   AlarmAction getAlarmAction(Integer ID);
}
