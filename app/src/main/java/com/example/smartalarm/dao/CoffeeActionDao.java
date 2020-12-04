package com.example.smartalarm.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.smartalarm.action.CoffeeAction;
import com.example.smartalarm.dataStructures.NameIdPair;

import java.util.List;

@Dao
public interface CoffeeActionDao {
   @Insert
   void insert(CoffeeAction alarm);

   @Update
   void update(CoffeeAction alarm);

   @Query("SELECT Name, ActionID FROM coffee_action")
   LiveData<List<NameIdPair>> getAllNames();

   @Query("SELECT * FROM coffee_action")
   LiveData<List<CoffeeAction>> getAllActions();

   @Query("SELECT * FROM coffee_action WHERE ActionID = :ID")
   CoffeeAction getAction(Integer ID);
}
