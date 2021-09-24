package com.example.myapplication.View

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.example.myapplication.R
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class PostActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @Test
    fun recyleViewTest(){
        //Espresso.onView((withId(R.id.recyclerViewSearchResults)))
        //  .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView
        //.ViewHolder>(2,click()))

        var recyclerView : RecyclerView = activityRule.activity.findViewById(R.id.recyclerViewPostsResults)
        var itemcount = recyclerView.adapter?.itemCount

        if (itemcount != null){
            Espresso.onView(ViewMatchers.withId(R.id.recyclerViewPostsResults))
                .perform(
                    RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>
                    (itemcount.minus(1)))
        }

        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewPostsResults))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView
            .ViewHolder>(3, ViewActions.click()))

        val postitem : String = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"
        Espresso.onView(ViewMatchers.withText(postitem)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @After
    fun tearDown() {
    }
}