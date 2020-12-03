package com.example.smartalarm.database;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import com.example.smartalarm.action.AlarmAction;
import com.example.smartalarm.dao.AlarmDao;
import com.example.smartalarm.dataStructures.NameIdPair;

import java.util.List;
import java.util.concurrent.Executor;

public class AlarmRepository {
   private AlarmDao AD;
   private LiveData<List<NameIdPair>> mAlarmNames;

   public AlarmRepository(Application app){
      SmartAlarmDatabase db = SmartAlarmDatabase.getDatabase(app);
      AD = db.alarmDao();
      mAlarmNames = AD.getAllNames();
   }

   public LiveData<List<NameIdPair>> getAlarmNames(){
      return mAlarmNames;
   }

   public void getAlarm(int id, iGetAction resp){
      new AsyncTask().execute(() -> {
         AlarmAction alarm = AD.getAlarmAction(id);
         new Handler(Looper.getMainLooper()).post(() -> resp.response(alarm));
      });
   }

   public void insert(AlarmAction alarm){
      new AsyncTask().execute(new Runnable(){
            @Override
            public void run(){AD.insert(alarm);}
      });
   }

   public void update(AlarmAction alarm){
      new AsyncTask().execute(new Runnable(){
         @Override
         public void run(){AD.update(alarm);}
      });
   }

   private static class AsyncTask implements Executor {
      @Override
      public void execute(Runnable command) {
         new Thread(command).start();
      }
   }
}
