package com.example.smartalarm;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartalarm.action.Action;
import com.example.smartalarm.action.AlarmAction;
import com.example.smartalarm.database.iGetAction;
import com.example.smartalarm.deviceAction.Sound;
import com.example.smartalarm.deviceAction.Vibrate;
import com.example.smartalarm.viewModels.AlarmActionViewModel;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ViewAlarmActionActivity extends AppCompatActivity
      implements AdapterView.OnItemSelectedListener, iGetAction {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_add_alarm_action);
      ID = getIntent().getIntExtra("ID", 0);
      mAAVM = new ViewModelProvider(this).get(AlarmActionViewModel.class);
      mAAVM.getAlarm(ID, this);
      mRawResourceMap = new HashMap<>();
      mRawResourceIdNameMap = new HashMap<>();
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
         mRawResourceIdNameMap.put(rawId,sName);
         adapter.add(sName);
      }
      mAlarmSound.setAdapter(adapter);
   }

   //Response code: https://stackoverflow.com/questions/12575068/how-to-get-the-result-of-onpostexecute-to-main-activity-because-asynctask-is-a/12575319#12575319
   @Override
   public void response(Action alarm) {
      mAlarm = (AlarmAction) alarm;
      mAlarm.setSoundResourceManager(Sound.getInstance(this));
      mAlarm.setVibrateResourceManager(Vibrate.getInstance(this));
      mName.setText(mAlarm.getName());
      mVolume.setProgress(mAlarm.getVolume());
      String soundName = mRawResourceIdNameMap.get(mAlarm.getSoundResource());
      int pos = 0;
      for(int i = 0; i < mAlarmSound.getCount(); i++){
         if(mAlarmSound.getItemAtPosition(i).toString().equals(soundName)){
            pos = i;
            break;
         }
      }
      mAlarmSound.setSelection(pos);
      mVibrate.setChecked(mAlarm.getVibrate());
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
      mAlarm.setName(mName.getText().toString());
      int volume = mVolume.getProgress();
      mAlarm.toggleVibrate(mVibrate.isChecked());
      mAlarm.setVolume(volume);
      // TODO: Store in db
      mAAVM.update(mAlarm);
      finish();
   }

   public void goBack(View view) {
      finish();
   }

   private SeekBar mVolume;
   private EditText mName;
   private Spinner mAlarmSound;
   private Switch mVibrate;
   private Map<String, Integer> mRawResourceMap;
   private Map<Integer, String> mRawResourceIdNameMap;
   private AlarmAction mAlarm;
   private AlarmActionViewModel mAAVM;
   private int ID;
}
