package com.example.smartalarm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartalarm.adapter.EventAdapter;
import com.example.smartalarm.event.Event;
import com.example.smartalarm.viewModels.EventViewModel;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   private static final String LOG_TAG = MainActivity.class.getSimpleName();
   public static final int TEXT_REQUEST = 1;
   private RecyclerView mEventRecyclerView;
   private LiveData<List<Event>> events;
   private EventViewModel mEVM;
   private HashMap<Event, AppCompatActivity> mEventDict =  new HashMap<>();
   private EventAdapter mEventAdapter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      mEVM = new ViewModelProvider(this).get(EventViewModel.class);
      mEVM.getEvents().observe(this, new Observer<List<Event>>() {
         @Override
         public void onChanged(List<Event> Events) {
            for (Event e : Events) {
               mEventDict.put(e, new ViewEventActivity());
            }
            mEventAdapter.setEvents(mEventDict);
         }
      });

      setEventRecyclerView();
   }

   private void setEventRecyclerView(){
      // Put initial data into the word list.
      // Create recycler view.
      mEventRecyclerView = findViewById(R.id.eventRecyclerView);
      // Create an adapter and supply the data to be displayed.
      mEventAdapter = new EventAdapter(this, mEventDict);
      // Connect the adapter with the recycler view.
      mEventRecyclerView.setAdapter(mEventAdapter);
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