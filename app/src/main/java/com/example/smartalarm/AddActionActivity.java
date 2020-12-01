package com.example.smartalarm;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartalarm.dataStructures.NameIdPair;
import com.example.smartalarm.viewModels.AlarmActionViewModel;

import java.util.HashMap;
import java.util.List;

public class AddActionActivity extends AppCompatActivity {
    private HashMap<String, AppCompatActivity> mActionDict =  new HashMap<>();
    private HashMap<String, AppCompatActivity> mExistingActionDict =  new HashMap<>();
    private RecyclerView mRecyclerView;
    private RecyclerView mActionRecyclerView;
    private ActionListAdapter mAdapter;
    private ActionListAdapter mActionAdapter;
    private AlarmActionViewModel mAAVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_action);

        // TODO: Add Existing Actions and its Activity
        mActionDict.put("Coffee Action", new AddCoffeeActionActivity());
        mActionDict.put("Alarm Action", new AddAlarmActionActivity());

        // Put initial data into the word list.
        // Create recycler view.
        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new ActionListAdapter(this, mActionDict);
        // Connect the adapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mActionRecyclerView = findViewById(R.id.actionrecyclerview);
        mActionAdapter = new ActionListAdapter(this, mExistingActionDict);
        mActionRecyclerView.setAdapter(mActionAdapter);
        mActionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAAVM = new ViewModelProvider(this).get(AlarmActionViewModel.class);
        mAAVM.getAlarmNames().observe(this, new Observer<List<NameIdPair>>() {
            @Override
            public void onChanged(List<NameIdPair> nameIdPairs) {
                mExistingActionDict = new HashMap<>();
                for(NameIdPair nid : nameIdPairs){
                    mExistingActionDict.put(nid.Name, new AddAlarmActionActivity());
                }
                mActionAdapter.setActions(mExistingActionDict);
            }
        });
    }

}

