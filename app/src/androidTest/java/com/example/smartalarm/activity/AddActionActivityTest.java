package com.example.smartalarm.activity;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.smartalarm.AddActionActivity;
import com.example.smartalarm.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddActionActivityTest {
    private static final String PACKAGE_NAME = "com.example.smartalarm";

    /* Instantiate an ActivityScenarioRule object. */
    @Rule
    public ActivityScenarioRule<AddActionActivity> activityRule =
            new ActivityScenarioRule<>(AddActionActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testCoffeeActionClick() {
        onView(withId(R.id.recyclerview)).perform(
                RecyclerViewActions.actionOnItemAtPosition(1, click()));

        intended(allOf(
                hasComponent(hasShortClassName(".AddCoffeeActionActivity")),
                toPackage(PACKAGE_NAME)));
    }

    @Test
    public void testAlarmActionClick() {
        onView(withId(R.id.recyclerview)).perform(
              RecyclerViewActions.actionOnItemAtPosition(0, click()));

        intended(allOf(
              hasComponent(hasShortClassName(".AddAlarmActionActivity")),
              toPackage(PACKAGE_NAME)));
    }
}
