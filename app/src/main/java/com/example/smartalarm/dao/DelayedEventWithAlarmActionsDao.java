package com.example.smartalarm.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.smartalarm.database.DelayedEventAlarmAction;
import com.example.smartalarm.database.DelayedEventWithAlarmActions;

import java.util.List;

// Data Access Objects are a part of the ORM
@Dao
public interface DelayedEventWithAlarmActionsDao {
   @Insert(onConflict = OnConflictStrategy.IGNORE)
   void insert(DelayedEventAlarmAction delayedEventAlarmAction);

   @Transaction
   @Query("Select * from delayed_event")
   public LiveData<List<DelayedEventWithAlarmActions>> getDelayedEventWithAlarmActions();
}
