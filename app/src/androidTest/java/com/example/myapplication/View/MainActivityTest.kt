package com.example.myapplication.View

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.myapplication.R
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test

class MainActivityTest : TestCase() {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    public override fun setUp() {
        super.setUp()
    }
    @Test
    fun recyleViewTest(){
        //Espresso.onView((withId(R.id.recyclerViewSearchResults)))
          //  .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView
            //.ViewHolder>(2,click()))

        var recyclerView : RecyclerView = activityRule.activity.findViewById(R.id.recyclerViewSearchResults)
        var itemcount = recyclerView.adapter?.itemCount

        if (itemcount != null){
            Espresso.onView(withId(R.id.recyclerViewSearchResults))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>
                    (itemcount.minus(1)))
        }

        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewSearchResults))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView
            .ViewHolder>(3, click()))

        val nameitem : String = "Leanne Graham"
        Espresso.onView(withText(nameitem)).check(matches(isDisplayed()))

    }

    public override fun tearDown() {}
}