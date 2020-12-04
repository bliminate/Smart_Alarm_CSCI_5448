package com.example.smartalarm.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.smartalarm.database.ImmediateEventAlarmAction;
import com.example.smartalarm.database.ImmediateEventWithActions;

import java.util.List;

@Dao
public interface ImmediateEventWithActionsDao {
   @Insert(onConflict = OnConflictStrategy.IGNORE)
   void insert(ImmediateEventAlarmAction immediateEventAlarmAction);

   @Transaction
   @Query("Select * from immediate_event")
   LiveData<List<ImmediateEventWithActions>> getImmediateEventWithActions();
}
