package com.example.smartalarm.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import com.example.smartalarm.database.DelayedEventAction;
import com.example.smartalarm.database.DelayedEventWithActions;

import java.util.List;

public interface DelayedEventWithActionsDao {
   @Insert(onConflict = OnConflictStrategy.IGNORE)
   void insert(DelayedEventAction delayedEventAction);

   @Transaction
   @Query("Select * from immediate_event")
   public LiveData<List<DelayedEventWithActions>> getDelayedEventWithActions();
}
