package com.example.smartalarm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartalarm.adapter.AlarmListAdapter;
import com.example.smartalarm.dataStructures.NameIdPair;
import com.example.smartalarm.viewModels.EventViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   private static final String LOG_TAG = MainActivity.class.getSimpleName();
   public static final int TEXT_REQUEST = 1;
   private RecyclerView mEventRecyclerView;
   private LiveData<List<Event>> events;
   private ConcreteEventListAdapter mEventListAdapter;
   private EventViewModel mEVM;
   private HashMap<Event, AppCompatActivity> mEventDict =  new HashMap<>();
   private EventAdapter mEventAdapter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      mEVM = new ViewModelProvider(this).get(EventViewModel.class);
      mEVM.getEvents().observe(this, Events -> {
         mEventDict = new HashMap<>();
         for(Event e : Events){
            mEventDict.put(e.getName(), new ViewAlarmActionActivity());
         }
         mEventAdapter.setActions(mEventDict);
      });

      setEventRecyclerView();
   }

   private void setEventRecyclerView(){
      // Put initial data into the word list.
      // Create recycler view.
      mEventRecyclerView = findViewById(R.id.eventRecyclerView);
      // Create an adapter and supply the data to be displayed.
      mEventListAdapter = new ConcreteEventListAdapter(this, events);
      // Connect the adapter with the recycler view.
      mEventRecyclerView.setAdapter(mEventListAdapter);
      // Give the recycler view a default layout manager.
      mEventRecyclerView.setLayoutManager(new LinearLayoutManager(this));
   };

   public void createAction(View view) {
      Log.d(LOG_TAG, "Create Action Button clicked!");
      Intent intent = new Intent(this, AddActionActivity.class);
      startActivityForResult(intent, TEXT_REQUEST);
   }

   public void createEvent(View view) {
      Log.d(LOG_TAG, "Create Event Button clicked!");
      Intent intent = new Intent(this, AddEventActivity.class);
      startActivityForResult(intent, TEXT_REQUEST);
   }

   @Override
   public void onActivityResult(int requestCode,
                                int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      if (requestCode == TEXT_REQUEST) {
         if (resultCode == RESULT_OK) {
            Event event = (Event)data.getSerializableExtra(AddEventActivity.CREATED_EVENT);
            Log.d(LOG_TAG, event.toString());
         }
      }
   }
}