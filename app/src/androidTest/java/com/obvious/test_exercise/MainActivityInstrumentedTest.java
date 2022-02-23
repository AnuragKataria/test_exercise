package com.obvious.test_exercise;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.obvious.test_exercise.utils.MyViewAction;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    // This will check the Images on recyclerview is visible or not
    @Test
    public void isImageViewVisible() {
        onView(allOf(withId(R.id.ivImage), isDisplayed()));
    }

    // This will check the Images on recyclerview is Clickable or not
    @Test
    public void isImageViewClickAble() {
        onView(allOf(withId(R.id.ivImage), isClickable()));

    }
    // This will check the Images on recyclerview  the click on images in Recyclerview, on deatil page
    // this will check the Viewpager text click and swipe left and right
    // I have added Thread.sleep(1000) for 1 second just to see the screen as it is getting disappers very quickly and we are not able to see the test cases
    @Test
    public void checkClickOnImageViewRecycleViewItems() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        appContext.startActivity(new Intent(appContext, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        // this will check click on images inside recyclerview
        onView(withId(R.id.recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.ivImage)));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // this will perform click on textview inside viewpager
        onView(allOf(withId(R.id.viewMoreTV), isDisplayed())).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // This will check the swipLeft
        onView(withId(R.id.viewPager)).perform(swipeLeft());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // This will check the swipright
        onView(withId(R.id.viewPager)).perform(swipeRight());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // this will perform click on textview inside viewpager
        onView(allOf(withId(R.id.viewMoreTV), isDisplayed())).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}