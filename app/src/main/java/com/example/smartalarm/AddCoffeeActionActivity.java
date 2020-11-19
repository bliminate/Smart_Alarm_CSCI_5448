package com.example.smartalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

public class AddCoffeeActionActivity extends AppCompatActivity {
    private EditText mName;
    private SeekBar mWaterAmount;
    private SeekBar mGroundAmount;
    public static final String CREATED_ACTION = "CREATED_ACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coffee_action);

        mName = findViewById(R.id.editActionName);
        mWaterAmount = findViewById(R.id.waterAmountSeekBar);
        mGroundAmount = findViewById(R.id.groundAmountSeekBar);
    }

    public void saveAction(View view) {
        String name = mName.getText().toString();
        int waterAmount = mWaterAmount.getProgress();
        int groundAmount = mGroundAmount.getProgress();

        CoffeeAction coffeeAction = new CoffeeAction(name, waterAmount, groundAmount);

        // TODO: Store in db

        finish();
    }

    public void goBack(View view) {
        finish();
    }
}