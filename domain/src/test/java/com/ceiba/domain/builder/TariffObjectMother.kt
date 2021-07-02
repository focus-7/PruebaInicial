package com.ceiba.domain.builder

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.model.Car
import com.ceiba.domain.model.Motorcycle
import java.text.SimpleDateFormat
import java.util.*

class TariffObjectMother {
    companion object {
        fun vehicleTypeCar(): Car {
            return VehicleBuilder()
                .withPlate("FER789")
                .buildCar()
        }

        private fun vehicleTypeMotorcycle150(): Motorcycle {
            return VehicleBuilder()
                .withCylinderCapacity(150)
                .buildMotorcycle()
        }

        private fun vehicleTypeMotorcycle750(): Motorcycle {
            return VehicleBuilder()
                .withCylinderCapacity(750)
                .buildMotorcycle()
        }

        private fun vehicleTypeMotorcycleWithPlateA(): Motorcycle {
            return VehicleBuilder()
                .withPlate("ATY38E")
                .buildMotorcycle()
        }

        fun tariffOfCar(): Tariff {
            return TariffBuilder()
                .build(vehicleTypeCar())
        }

        fun tariffOfMotorcycleCC150(): Tariff {
            return TariffBuilder()
                .build(vehicleTypeMotorcycle150())
        }

        fun tariffOfMotorcycleCC750(): Tariff {
            return TariffBuilder()
                .build(vehicleTypeMotorcycle750())
        }

        fun tariffOfMotorcycleOnTuesdayWithPlateA(): Tariff {
            return TariffBuilder()
                .withEntryDate(1624352400000)//June 22, 2021, 9:00 a.m
                .build(vehicleTypeMotorcycleWithPlateA())
        }

        fun tariffOfMotorcycleOnSundayWithPlateA(): Tariff {
            return TariffBuilder()
                .withEntryDate(1624179600000)//June 20, 2021, 9:00 a.m (Sunday)
                .build(vehicleTypeMotorcycleWithPlateA())
        }

        fun tariffOfMotorcycleOnMondayWithPlateA(): Tariff {
            return TariffBuilder()
                .withEntryDate(1623661200000)//June 14, 2021, 9:00 a.m (Monday)
                .build(vehicleTypeMotorcycleWithPlateA())
        }

        fun departureVehicleInJuneAtOnePm(tariff: Tariff) {
            tariff.vehicleDepartureDate = 1624366800000 //June 22, 2021, 1:00 p.m
        }

        fun departureVehicleInJune901am(tariff: Tariff) {
            tariff.vehicleDepartureDate = 1624352460000 //June 22, 2021, 9:01 a.m
        }

        fun departureVehicleInJuneAtEightPm(tariff: Tariff) {
            tariff.vehicleDepartureDate = 1624392000000 //June 22, 2021, 8:00 p.m
        }

        fun departureVehicleInJuneAtFivePm(tariff: Tariff) {
            tariff.vehicleDepartureDate = 1624381200000 //June 22, 2021, 5:00 p.m
        }

        fun getStringDepartureDateVehicle(date: Long): String {
            val format = SimpleDateFormat("dd/MM/yyyy HH:mm a", Locale.getDefault())
            val formatDate = Date(date)
            return format.format(formatDate)
        }
    }
}
