package com.example.smartalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class AddEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
    }

    public void saveAction(View view) {
    }

    public void createAction(View view) {
        // Create an Intent to pass to AddActionActivity
        // to create an action
        // Once done, return to this page

        // Be aware that AddActionActivity will also be
        // called from MainActivity

        // When ended, Updated the Spinner so that
        // the newly created action is shown in the list
    }
}