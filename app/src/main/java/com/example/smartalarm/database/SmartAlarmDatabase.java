package com.example.smartalarm.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.smartalarm.action.AlarmAction;
import com.example.smartalarm.action.CoffeeAction;
import com.example.smartalarm.dao.*;
import com.example.smartalarm.event.DelayedEvent;
import com.example.smartalarm.event.ImmediateEvent;

//DB Code taken from: https://developer.android.com/codelabs/android-training-livedata-viewmodel?index=..%2F..%2Fandroid-training#6
// This is a singleton db class to provide a db instance to communicate with
@Database(entities = {AlarmAction.class, CoffeeAction.class, DelayedEvent.class,
      ImmediateEvent.class, ImmediateEventAlarmAction.class, DelayedEventAlarmAction.class,
      ImmediateEventCoffeeAction.class, DelayedEventCoffeeAction.class},
      version = 7, exportSchema = false)
@TypeConverters({CalendarTypeConverter.class})
public abstract class SmartAlarmDatabase extends RoomDatabase {
   public abstract AlarmDao alarmDao();
   public abstract CoffeeActionDao coffeeActionDao();
   public abstract DelayedEventDao delayedEventDao();
   public abstract ImmediateEventDao immediateEventDao();
   public abstract ImmediateEventWithAlarmActionsDao immediateEventWithAlarmActionsDao();
   public abstract ImmediateEventWithCoffeeActionsDao immediateEventWithCoffeeActionsDao();
   public abstract DelayedEventWithAlarmActionsDao delayedEventWithAlarmActionsDao();
   public abstract DelayedEventWithCoffeeActionsDao delayedEventWithCoffeeActionsDao();
   private static SmartAlarmDatabase instance;

   public static SmartAlarmDatabase getDatabase(final Context context){
      if(instance == null){
         synchronized (SmartAlarmDatabase.class){
            if(instance == null){
               instance = Room.databaseBuilder(context.getApplicationContext(),
                     SmartAlarmDatabase.class, "smart_alarm_database")
                     .fallbackToDestructiveMigration()
                     .build();
            }
         }
      }
      return instance;
   }
}
