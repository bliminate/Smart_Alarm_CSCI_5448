package com.example.smartalarm.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.smartalarm.action.AlarmAction;
import com.example.smartalarm.dao.AlarmDao;
import com.example.smartalarm.dao.DelayedEventDao;
import com.example.smartalarm.dao.ImmediateEventDao;

//DB Code taken from: https://developer.android.com/codelabs/android-training-livedata-viewmodel?index=..%2F..%2Fandroid-training#6
// This is a singleton db class to provide a db instance to communicate with
@Database(entities = {AlarmAction.class}, version = 1, exportSchema = false)
public abstract class SmartAlarmDatabase extends RoomDatabase {
   public abstract AlarmDao alarmDao();
   public abstract DelayedEventDao delayedEventDao();
   public abstract ImmediateEventDao immediateEventDao();
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
