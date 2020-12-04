package com.example.smartalarm.database;

import android.app.Application;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.smartalarm.action.Action;
import com.example.smartalarm.action.AlarmAction;
import com.example.smartalarm.action.CoffeeAction;
import com.example.smartalarm.dao.AlarmDao;
import com.example.smartalarm.dao.CoffeeActionDao;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

public class ActionRepository {
   private AlarmDao AD;
   private CoffeeActionDao CAD;
   private final LiveData<List<Action>> mActions;

   @RequiresApi(api = Build.VERSION_CODES.N)
   public ActionRepository(Application app){
      SmartAlarmDatabase db = SmartAlarmDatabase.getDatabase(app);
      AD = db.alarmDao();
      CAD = db.coffeeActionDao();
      mActions = getAllActions();
   }

   public void getAction(String actionClass, Integer id, iGetAction resp){
      new ActionRepository.AsyncTask().execute(() -> {
         Action action = null;
         if (actionClass == "AlarmAction"){
            action = AD.getAlarmAction(id);
         } else if (actionClass == "CoffeeAction") {
            action = CAD.getAction(id);
         }
         Action finalAction = action;
         new Handler(Looper.getMainLooper()).post(() -> resp.response(finalAction));
      });
   }


   public LiveData<List<Action>> getActions(){
      return mActions;
   }

   //Mediator Live data:
   // https://developer.android.com/reference/androidx/lifecycle/MediatorLiveData
   @RequiresApi(api = Build.VERSION_CODES.N)
   private LiveData<List<Action>> getAllActions(){
      MediatorLiveData<List<Action>> ret = new MediatorLiveData<>();
      LiveData<List<AlarmAction>> alarmActions = AD.getAllActions();
      LiveData<List<CoffeeAction>> coffeeActions = CAD.getAllActions();
      ret.addSource(alarmActions, value -> ret.setValue(value.stream().map(x -> (Action)x).collect(Collectors.toList())));
      ret.addSource(coffeeActions, value -> ret.setValue(value.stream().map(x -> (Action)x).collect(Collectors.toList())));
      return ret;
   }

   public void insert(Action action){
      if(action instanceof AlarmAction) {
         new ActionRepository.AsyncTask().execute(() -> AD.insert((AlarmAction) action));
      }
      else if(action instanceof CoffeeAction){
         new ActionRepository.AsyncTask().execute(() -> CAD.insert((CoffeeAction) action));
      }
   }

   public void update(Action action){
      if(action instanceof AlarmAction) {
         new ActionRepository.AsyncTask().execute(() -> AD.update((AlarmAction) action));
      }
      else if(action instanceof CoffeeAction){
         new ActionRepository.AsyncTask().execute(() -> CAD.update((CoffeeAction) action));
      }
   }

   private static class AsyncTask implements Executor {
      @Override
      public void execute(Runnable command) {
         new Thread(command).start();
      }
   }
}
