package com.example.smartalarm.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.smartalarm.action.AlarmAction;
import com.example.smartalarm.dataStructures.NameIdPair;
import com.example.smartalarm.database.AlarmRepository;
import com.example.smartalarm.database.iGetAction;

import java.util.List;

public class AlarmActionViewModel extends AndroidViewModel {
   private AlarmRepository repo;
   private LiveData<List<NameIdPair>> alarmNames;

   public AlarmActionViewModel( Application application) {
      super(application);
      repo = new AlarmRepository(application);
      alarmNames = repo.getAlarmNames();
   }

   public void insert(AlarmAction alarm){
      repo.insert(alarm);
   }

   public void update(AlarmAction alarm){
      repo.update(alarm);
   }

   public LiveData<List<NameIdPair>> getAlarmNames(){
      return alarmNames;
   }

   public void getAlarm(int id, iGetAction resp){
      repo.getAlarm(id, resp);
   }
}
