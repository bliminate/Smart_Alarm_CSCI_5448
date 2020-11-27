package com.example.smartalarm.activity;

import android.app.Activity;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.smartalarm.AddEventActivity;
import com.example.smartalarm.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.ActivityResultMatchers.hasResultCode;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertThat;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddEventActivityTest {
    private static final String PACKAGE_NAME = "com.example.smartalarm";

    /* Instantiate an ActivityScenarioRule object. */
    @Rule
    public ActivityScenarioRule<AddEventActivity> activityRule =
            new ActivityScenarioRule<>(AddEventActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testClickAddAction(){
        onView(withId(R.id.addActionButton)).perform(click());
        intended(allOf(
                hasComponent(hasShortClassName(".AddActionActivity")),
                toPackage(PACKAGE_NAME)));
    }

    @Test
    public void testClickStopCreateEvent(){
        onView(withId(R.id.stopCreateEventButton)).perform(click());
        intended(allOf(
                hasComponent(hasShortClassName(".MainActivity")),
                toPackage(PACKAGE_NAME)));
    }

    @Test
    public void testClickSaveEvent(){
        onView(withId(R.id.saveEventButton)).perform(click());
        assertThat(activityRule.getScenario().getResult(), hasResultCode(Activity.RESULT_OK));
    }
}
