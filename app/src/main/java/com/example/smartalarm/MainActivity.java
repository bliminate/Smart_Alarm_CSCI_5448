package com.example.smartalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
   private static final String LOG_TAG = MainActivity.class.getSimpleName();
   public static final int TEXT_REQUEST = 1;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      
   }

   public void createAction(View view) {
      Log.d(LOG_TAG, "Create Action Button clicked!");
      Intent intent = new Intent(this, AddActionActivity.class);
      startActivity(intent);
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