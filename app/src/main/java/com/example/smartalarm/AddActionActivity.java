package com.example.smartalarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.HashMap;

public class AddActionActivity extends AppCompatActivity {
    private HashMap<String, AppCompatActivity> mActionDict =  new HashMap<>();
    private RecyclerView mRecyclerView;
    private ActionListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_action);

        // TODO: Add Existing Actions and its Activity
        mActionDict.put("Coffee Action", new AddCoffeeActionActivity());

        // Put initial data into the word list.
        // Create recycler view.
        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new ActionListAdapter(this, mActionDict);
        // Connect the adapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}

