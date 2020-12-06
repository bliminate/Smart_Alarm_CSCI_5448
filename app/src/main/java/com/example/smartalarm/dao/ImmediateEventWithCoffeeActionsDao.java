package com.example.smartalarm.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.smartalarm.database.ImmediateEventCoffeeAction;
import com.example.smartalarm.database.ImmediateEventWithCoffeeActions;

import java.util.List;

// Data Access Objects are a part of the ORM
@Dao
public interface ImmediateEventWithCoffeeActionsDao {
   @Insert(onConflict = OnConflictStrategy.IGNORE)
   void insert(ImmediateEventCoffeeAction immediateEventCoffeeAction);

   @Transaction
   @Query("Select * from immediate_event")
   LiveData<List<ImmediateEventWithCoffeeActions>> getImmediateEventWithCoffeeActions();
}
