package com.example.smartalarm;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartalarm.action.Action;
import com.example.smartalarm.action.CoffeeAction;
import com.example.smartalarm.database.iGetAction;
import com.example.smartalarm.viewModels.ActionViewModel;

public class ViewCoffeeActionActivity extends AppCompatActivity implements iGetAction {
    private int ID;
    private EditText mName;
    private SeekBar mWaterAmount;
    private SeekBar mGroundAmount;
    public static final String CREATED_ACTION = "CREATED_ACTION";

    private ActionViewModel mAVM;
    private CoffeeAction mAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coffee_action);

        ID = getIntent().getIntExtra("ID", 0);
        mName = findViewById(R.id.editActionName);
        mWaterAmount = findViewById(R.id.waterAmountSeekBar);
        mGroundAmount = findViewById(R.id.groundAmountSeekBar);

        mAVM = new ViewModelProvider(this).get(ActionViewModel.class);
        mAVM.getAction( "CoffeeAction", ID, this);
    }

    public void saveAction(View view) {
        String name = mName.getText().toString();
        int waterAmount = mWaterAmount.getProgress();
        int groundAmount = mGroundAmount.getProgress();

        CoffeeAction coffeeAction = new CoffeeAction("http://10.0.2.2:8000");
        coffeeAction.setName(name);
        coffeeAction.setWater(waterAmount);
        coffeeAction.setGround(groundAmount);

        // Store in db
        mAVM.insert(coffeeAction);

        // Trigger Action just for demo
        // coffeeAction.executeAction();

        // Return
        finish();
    }

    public void goBack(View view) {
        finish();
    }

    @Override
    public void response(Action action) {
        mAction = (CoffeeAction) action;

        fillActionInfo();
    }

    private void fillActionInfo(){
        if (mAction == null) return;

        mName.setText(mAction.getName());
        mWaterAmount.setProgress(mAction.getWater());
        mGroundAmount.setProgress(mAction.getGround());
    }

}