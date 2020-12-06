package com.example.smartalarm.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.smartalarm.database.ImmediateEventAlarmAction;
import com.example.smartalarm.database.ImmediateEventWithAlarmActions;

import java.util.List;

// Data Access Objects are a part of the ORM
@Dao
public interface ImmediateEventWithAlarmActionsDao {
   @Insert(onConflict = OnConflictStrategy.IGNORE)
   void insert(ImmediateEventAlarmAction immediateEventAlarmAction);

   @Transaction
   @Query("Select * from immediate_event")
   LiveData<List<ImmediateEventWithAlarmActions>> getImmediateEventWithAlarmActions();
}
