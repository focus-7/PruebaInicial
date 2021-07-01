package com.ceiba.domain.model

import java.io.Serializable


abstract class Vehicle(var plate: String, val vehicleType: Int) : Serializable {
    companion object {
        const val PLATE_INITIAL_LETTER = 'A'
    }

    fun plateInitWithA(): Boolean {
        return plate[0] == PLATE_INITIAL_LETTER
    }
}