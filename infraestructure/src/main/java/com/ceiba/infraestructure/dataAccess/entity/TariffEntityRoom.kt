package com.ceiba.infraestructure.dataAccess.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tariff")
data class TariffEntityRoom(
    @PrimaryKey
    val plate: String,
    val entryDate: Long,
    var type: Int
) {
    var departureDate: Long? = null
    var cylinderCapacity: Int? = null
}