package com.example.smartalarm;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartalarm.action.Action;
import com.example.smartalarm.action.AlarmAction;
import com.example.smartalarm.action.CoffeeAction;
import com.example.smartalarm.adapter.ActionAdapter;
import com.example.smartalarm.adapter.ActionListAdapter;
import com.example.smartalarm.viewModels.ActionViewModel;

import java.util.HashMap;

public class AddActionActivity extends AppCompatActivity {
    private HashMap<String, AppCompatActivity> mActionDict =  new HashMap<>();
    private HashMap<Action, AppCompatActivity> mExistingActionDict =  new HashMap<>();
    private RecyclerView mRecyclerView;
    private RecyclerView mActionRecyclerView;
    private ActionAdapter mAdapter;
    private ActionListAdapter mActionAdapter;
    private ActionViewModel mAVM;

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
        mActionAdapter = new ActionListAdapter(this, mExistingActionDict);
        mActionRecyclerView.setAdapter(mActionAdapter);
        mActionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAVM = new ViewModelProvider(this).get(ActionViewModel.class);
        mAVM.getActions().observe(this, actions -> {
            mExistingActionDict = new HashMap<>();
            for(Action a : actions){
                if(a instanceof AlarmAction){
                    mExistingActionDict.put(a, new ViewAlarmActionActivity());
                }
                else if(a instanceof CoffeeAction){
                    //TODO: Implement Coffee action
                    //mExistingActionDict.put(a, new ViewCoffeeActionActivity());
                }
            }
            mActionAdapter.setActions(mExistingActionDict);
        });
    }

}

