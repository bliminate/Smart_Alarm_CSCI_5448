package com.example.smartalarm;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartalarm.action.CoffeeAction;
import com.example.smartalarm.viewModels.ActionViewModel;

// Controller of the MVC Pattern
public class AddCoffeeActionActivity extends AppCompatActivity {
    private EditText mName;
    private SeekBar mWaterAmount;
    private SeekBar mGroundAmount;
    public static final String CREATED_ACTION = "CREATED_ACTION";

    private ActionViewModel mAVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coffee_action);

        mName = findViewById(R.id.editActionName);
        mWaterAmount = findViewById(R.id.waterAmountSeekBar);
        mGroundAmount = findViewById(R.id.groundAmountSeekBar);

        mAVM = new ViewModelProvider(this).get(ActionViewModel.class);
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
}