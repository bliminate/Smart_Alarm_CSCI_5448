package com.example.smartalarm.activity;

import android.view.View;
import android.widget.SeekBar;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.CoordinatesProvider;
import androidx.test.espresso.action.GeneralClickAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Tap;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.smartalarm.AddCoffeeActionActivity;
import com.example.smartalarm.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddCoffeeActionActivityTest {
    private static final String PACKAGE_NAME = "com.example.smartalarm";
    private String actionName;
    private int waterAmount;
    private int groundAmount;

    /* Instantiate an ActivityScenarioRule object. */
    @Rule
    public ActivityScenarioRule<AddCoffeeActionActivity> activityRule =
            new ActivityScenarioRule<>(AddCoffeeActionActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        actionName = "Action Name";
        waterAmount = 100;
        groundAmount = 100;
    }

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testFillAllAndClickSave() {
        onView(withId(R.id.editActionName))
                .perform(typeText(actionName), closeSoftKeyboard());
        onView(withId(R.id.waterAmountSeekBar))
                .perform(clickSeekBar(waterAmount));
        onView(withId(R.id.groundAmountSeekBar))
                .perform(clickSeekBar(groundAmount));
        onView(withId(R.id.saveActionButton)).perform(click());
    }


    // Taken from https://python5.com/q/rsshpmqd
    public static ViewAction clickSeekBar(final int pos) {
        return new GeneralClickAction(
                Tap.SINGLE,
                new CoordinatesProvider() {
                    @Override
                    public float[] calculateCoordinates(View view) {
                        SeekBar seekBar = (SeekBar) view;
                        final int[] screenPos = new int[2];
                        seekBar.getLocationOnScreen(screenPos);

                        // get the width of the actual bar area
                        // by removing padding
                        int trueWidth = seekBar.getWidth()
                                - seekBar.getPaddingLeft() - seekBar.getPaddingRight();

                        // what is the position on a 0-1 scale
                        //  add 0.3f to avoid roundoff to the next smaller position
                        float relativePos = (0.3f + pos) / (float) seekBar.getMax();
                        if (relativePos > 1.0f)
                            relativePos = 1.0f;

                        // determine where to click
                        final float screenX = trueWidth * relativePos + screenPos[0]
                                + seekBar.getPaddingLeft();
                        final float screenY = seekBar.getHeight() / 2f + screenPos[1];
                        float[] coordinates = {screenX, screenY};

                        return coordinates;
                    }
                },
                Press.FINGER);
    }
}
