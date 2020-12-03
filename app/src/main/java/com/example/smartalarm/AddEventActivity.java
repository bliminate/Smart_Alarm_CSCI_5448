package com.example.smartalarm;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartalarm.action.Action;
import com.example.smartalarm.event.DelayedEvent;
import com.example.smartalarm.event.Event;
import com.example.smartalarm.fragment.DatePickerFragment;
import com.example.smartalarm.fragment.TimePickerFragment;
import com.example.smartalarm.viewModels.ActionViewModel;
import com.example.smartalarm.viewModels.EventViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class AddEventActivity extends AppCompatActivity
    implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener,
        AdapterView.OnItemSelectedListener {

    private static final String LOG_TAG = AddEventActivity.class.getSimpleName();
    public static final String CREATED_EVENT = "CREATED_EVENT";
    public static final int TEXT_REQUEST = 1;
    private String actionKey;

    private EditText mEventName;
    private EditText mEventDate;
    private EditText mEventTime;
    private Spinner mSpinner;
    private Calendar calendar;

    private List<Action> actions;
    private List<String> actionNames;
    private ActionViewModel mAVM;
    private EventViewModel mEVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        actionKey = "";
        calendar =  Calendar.getInstance();

        mEventName = findViewById(R.id.editEventName);
        mEventDate = findViewById(R.id.editEventDate);
        mEventTime = findViewById(R.id.editEventTime);

        // Read a list of actions from db
        actions = new ArrayList<>();
        mAVM = new ViewModelProvider(this).get(ActionViewModel.class);
        mEVM = new ViewModelProvider(this).get(EventViewModel.class);

        // Set Spinner
        mSpinner = (Spinner) findViewById(R.id.spinner);
        if (mSpinner != null) {
            mSpinner.setOnItemSelectedListener(this);
        }

        actionNames = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                actionNames);

        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        // This may not retrieve the updated data
        // https://stackoverflow.com/questions/59350020/populate-spinner-from-livedata-room-database
        mAVM.getActions().observe(this, new Observer<List<Action>>() {
            @Override
            public void onChanged(List<Action> _actions) {
                for (Action action : _actions) {
                    actionNames.add(action.getName());
                }
                adapter.notifyDataSetChanged();
            }
        });


//        // Load action names onto the spinner object
//        loadSpinnerData();
    }

    public void saveEvent(View view) {
        // Extract All information from the view
        String eventName = mEventName.getText().toString();

        //TODO: logic to create delayed vs immediate event
        // Create an event
        Event event = new DelayedEvent();
        event.setDelay(calendar);
        event.setName(eventName);

        // Set info as a single Intent object
        Intent replyIntent = new Intent();
        replyIntent.putExtra(CREATED_EVENT, (Serializable) event);

        // Save it in db
        mEVM.insert(event);

        // Return the intent back to the original activity (MainActivity)
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    public void createAction(View view) {
        // Create an Intent to pass to AddActionActivity
        // to create an action
        Intent intent = new Intent(this, AddActionActivity.class);
        // Once done, return to this page
        startActivityForResult(intent, TEXT_REQUEST);

        // When ended, Updated the Spinner so that
        // the newly created action is shown in the list
        loadSpinnerData();
    }

    // TODO
    private void loadSpinnerData() {
        // Create an ArrayAdapter using the string array and default spinner
        // layout.
        List<String> actionNames = new ArrayList<>();
        for (Action action : actions) {
            actionNames.add(action.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                actionNames);

        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner.
        if (mSpinner != null) {
            mSpinner.setAdapter(adapter);
        }
    }

    public void stopCreateEvent(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    public void showTimePickerDialog(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        mEventDate.setText("" + dayOfMonth + "/" + month + "/" + year);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        mEventTime.setText("" + hourOfDay + ":" + minute);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        actionKey = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        actionKey = "";
    }
}