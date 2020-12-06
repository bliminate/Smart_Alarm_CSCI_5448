package com.example.smartalarm.viewModels;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.smartalarm.action.Action;
import com.example.smartalarm.clock.MinuteClock;
import com.example.smartalarm.database.ActionRepository;
import com.example.smartalarm.database.iGetAction;

import java.util.List;

public class ActionViewModel extends AndroidViewModel {
    private ActionRepository repo;
    private LiveData<List<Action>> mActions;
    private MinuteClock clock;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ActionViewModel(@NonNull Application application) {
        super(application);
        repo = new ActionRepository(application);
        mActions = repo.getActions();
        clock = MinuteClock.getInstance();
    }

    public void insert(Action action){
        repo.insert(action);
    }

    public void update(Action action){
        repo.update(action);
    }

    public LiveData<List<Action>> getActions(){
        return mActions;
    }

    public void getAction(String coffeeAction, Integer id, iGetAction resp){
        repo.getAction(coffeeAction, id, resp);
    }
}
