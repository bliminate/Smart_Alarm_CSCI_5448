package com.example.smartalarm;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;
import com.example.smartalarm.action.AlarmAction;
import com.example.smartalarm.deviceAction.Sound;
import com.example.smartalarm.deviceAction.Vibrate;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class AddAlarmActionActivity extends AppCompatActivity
 implements AdapterView.OnItemSelectedListener {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_add_alarm_action);

      mAlarm = new AlarmAction(new Vibrate(this), new Sound(this));
      mRawResourceMap = new HashMap<>();
      mName = findViewById(R.id.editActionName);
      mVolume = findViewById(R.id.VolumeLevelSeekBar);
      mAlarmSound = findViewById(R.id.AlarmSoundSpinner);
      mVibrate = findViewById(R.id.vibrateSwitch);

      if(mAlarmSound != null){
         mAlarmSound.setOnItemSelectedListener(this);
      }

      try {
         loadSpinnerData();
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      }
   }

   private void loadSpinnerData() throws IllegalAccessException {
      //https://developer.android.com/guide/topics/ui/controls/spinner
      ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item);

      //Get all the sound resources and add them to the array adapter
      //https://stackoverflow.com/questions/25828121/get-file-list-in-android-raw-resource-directory-from-code
      Field[] sounds = R.raw.class.getFields();
      for (Field f : sounds) {
         String sName = f.getName();
         @RawRes int rawId = (Integer) f.get(null);
         mRawResourceMap.put(sName, rawId);
         adapter.add(sName);
      }
      mAlarmSound.setAdapter(adapter);
   }

   @Override
   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
      mAlarm.setSoundResource(mRawResourceMap.get(parent.getItemAtPosition(position).toString()));
   }

   @Override
   public void onNothingSelected(AdapterView<?> parent) {
      mAlarm.setSoundResource(0);
   }

   public void saveAction(View view) {
      String name = mName.getText().toString();
      int volume = mVolume.getProgress();
      mAlarm.toggleVibrate(mVibrate.isChecked());
      mAlarm.setVolume(volume);
      // TODO: Store in db

      finish();
   }

   private SeekBar mVolume;
   private EditText mName;
   private Spinner mAlarmSound;
   private Switch mVibrate;
   private Map<String, Integer> mRawResourceMap;
   private AlarmAction mAlarm;
}
