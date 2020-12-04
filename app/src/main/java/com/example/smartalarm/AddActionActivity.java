package com.example.smartalarm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartalarm.adapter.ActionAdapter;
import com.example.smartalarm.adapter.AlarmListAdapter;
import com.example.smartalarm.dataStructures.NameIdPair;
import com.example.smartalarm.viewModels.AlarmActionViewModel;

import java.util.HashMap;

public class AddActionActivity extends AppCompatActivity {
    private HashMap<String, AppCompatActivity> mActionDict =  new HashMap<>();
    private HashMap<NameIdPair, AppCompatActivity> mExistingActionDict =  new HashMap<>();
    private RecyclerView mRecyclerView;
    private RecyclerView mActionRecyclerView;
    private ActionAdapter mAdapter;
    private AlarmListAdapter mActionAdapter;
    private AlarmActionViewModel mAAVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_action);

        mActionDict.put("Alarm Action", new AddAlarmActionActivity());
        mActionDict.put("Coffee Action", new AddCoffeeActionActivity());
        mAdapter = new ActionAdapter(this, mActionDict);
        mAdapter.setActions(mActionDict);

        // Put initial data into the word list.
        // Create recycler view.
        mRecyclerView = findViewById(R.id.recyclerview);
        // Connect the adapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mActionRecyclerView = findViewById(R.id.actionrecyclerview);
        mActionAdapter = new AlarmListAdapter(this, mExistingActionDict);
        mActionRecyclerView.setAdapter(mActionAdapter);
        mActionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAAVM = new ViewModelProvider(this).get(AlarmActionViewModel.class);
        mAAVM.getAlarmNames().observe(this, nameIdPairs -> {
            mExistingActionDict = new HashMap<>();
            for(NameIdPair nid : nameIdPairs){
                mExistingActionDict.put(nid, new ViewAlarmActionActivity());
            }
            mActionAdapter.setActions(mExistingActionDict);
        });
    }

}

