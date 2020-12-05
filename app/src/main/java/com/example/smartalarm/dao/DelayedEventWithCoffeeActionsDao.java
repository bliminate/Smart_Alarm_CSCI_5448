package com.example.smartalarm.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.smartalarm.database.DelayedEventCoffeeAction;
import com.example.smartalarm.database.DelayedEventWithCoffeeActions;

import java.util.List;

@Dao
public interface DelayedEventWithCoffeeActionsDao {
   @Insert(onConflict = OnConflictStrategy.IGNORE)
   void insert(DelayedEventCoffeeAction delayedEventCoffeeAction);

   @Transaction
   @Query("Select * from delayed_event")
   LiveData<List<DelayedEventWithCoffeeActions>> getDelayedEventWithCoffeeActions();
}
