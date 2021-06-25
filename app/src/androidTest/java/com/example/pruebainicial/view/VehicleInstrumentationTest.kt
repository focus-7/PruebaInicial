package com.example.pruebainicial.view


import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.pruebainicial.MainActivity
import com.example.pruebainicial.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class VehicleInstrumentationTest {
    @get:Rule
    var activityScenarioRule:
            ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun addVehicleToListTest() {
        //Arrange
        onView(withId(R.id.btn_add_vehicle)).perform(click())

        //Act
        onView(withId(R.id.typeMotorcycle))
            .perform(click())
        onView(withId(R.id.plate))
            .perform(typeText("MSK472"))
        onView(withId(R.id.cylinderCapacity))
            .perform(typeText("550"), closeSoftKeyboard())

        //Assert
        onView(withId(R.id.btnAgregar)).perform(click())
        onView(isRoot()).perform(pressBack())
    }

    @Test
    fun takeOutVehicleTest() {
        //Arrange
        onView(withId(R.id.rv_vehicles))
            .check(matches(isDisplayed()))

        //Act
        onView(withId(R.id.rv_vehicles))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>
                    (0, clickOnViewChild(R.id.btn_take_out_vehicle))
            )

        //Assert
        onView(withId(R.id.btn_payment)).perform(click())
    }

    @Test
    fun emptyTest() {
        onView(withId(R.id.searchView)).perform(typeSearchViewText("empty"))

        onView(allOf(withText("Vehículo no encontrado"))).check(matches(withText("Vehículo no encontrado")))
    }

    private fun typeSearchViewText(text: String): ViewAction {
        return object : ViewAction {
            override fun getDescription(): String {
                return "Change view text"
            }

            override fun getConstraints(): Matcher<View> {
                return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
            }

            override fun perform(uiController: UiController?, view: View?) {
                (view as SearchView).setQuery(text, false)
            }
        }
    }


    private fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null
        override fun getDescription() = "Click on a child view with specified id."
        override fun perform(uiController: UiController, view: View) =
            click().perform(uiController, view.findViewById(viewId))
    }

}