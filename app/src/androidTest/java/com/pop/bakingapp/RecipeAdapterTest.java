package com.pop.bakingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class RecipeAdapterTest {
    public static final String Recipe_NAME = "Brownies";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
/*
    @Test
    public void clickGridViewItem_OpensOrderActivity() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        onData(anything()).inAdapterView(withId(R.id.gridview)).atPosition(1).perform(click());
        onView(withId(R.id.toolbar_title)).check(matches(withText(Recipe_NAME)));


    }*/
}
