package com.ceiba.pruebainicial.view


import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.ceiba.pruebainicial.HomeVehicleActivity
import com.ceiba.pruebainicial.R
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListItemCount
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListNotEmpty
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import com.schibsted.spain.barista.interaction.BaristaKeyboardInteractions.closeKeyboard
import com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItemChild
import com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleep
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@LargeTest
@RunWith(AndroidJUnit4::class)
class VehicleInstrumentationTest {
    @get:Rule
    var activityScenarioRule:
            ActivityScenarioRule<HomeVehicleActivity> =
        ActivityScenarioRule(HomeVehicleActivity::class.java)


    @Test
    fun addVehicleToListTest() {
        //Arrange
        clickOn(R.id.btn_add_vehicle)

        //Act
        clickOn(R.id.typeMotorcycle)
        writeTo(R.id.plate, "MSK472")
        writeTo(R.id.cylinderCapacity, "550")

        //Assert
        clickOn(R.id.buttonAdd)
    }

    @Test
    fun takeOutVehicleTest() {
        //Arrange
        clickListItemChild(R.id.rv_vehicles, 0, R.id.btn_take_out_vehicle);

        //Assert
        clickOn(R.id.buttonPayment)
    }

    @Test
    fun textEmptyTest() {
        //Arrange
        writeTo(R.id.searchView, "empty")
        closeKeyboard()

        //Assert
        assertListItemCount(R.id.rv_vehicles, 0)
    }
}