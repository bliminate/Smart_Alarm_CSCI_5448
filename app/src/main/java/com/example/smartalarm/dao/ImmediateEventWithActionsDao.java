package com.example.smartalarm.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.smartalarm.database.ImmediateEventAction;
import com.example.smartalarm.database.ImmediateEventWithActions;

import java.util.List;

@Dao
public interface ImmediateEventWithActionsDao {
   @Insert(onConflict = OnConflictStrategy.IGNORE)
   void insert(ImmediateEventAction immediateEventAction);

   @Transaction
   @Query("Select * from immediate_event")
   LiveData<List<ImmediateEventWithActions>> getImmediateEventWithActions();
}
