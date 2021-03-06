package com.example.smartalarm.viewModels;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.smartalarm.clock.MinuteClock;
import com.example.smartalarm.database.EventRepository;
import com.example.smartalarm.event.Event;

import java.util.List;

public class EventViewModel extends AndroidViewModel {
   private EventRepository repo;
   private LiveData<List<Event>> mEvents;
   private MinuteClock clock;

   @RequiresApi(api = Build.VERSION_CODES.N)
   public EventViewModel(@NonNull Application application) {
      super(application);
      repo = new EventRepository(application);
      mEvents = repo.getEvents();
      clock = MinuteClock.getInstance();
      clock.startClock();
   }

   public void insert(Event event){
      repo.insert(event);
   }

   public void update(Event event){
      repo.update(event);
   }

   public LiveData<List<Event>> getEvents(){
      return mEvents;
   }
}
