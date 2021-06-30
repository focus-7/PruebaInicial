package com.ceiba.domain.builder

import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.model.Car
import com.ceiba.domain.model.Motorcycle

class TariffObjectMother {
    companion object {
        private fun vehicleTypeCar(): Car {
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

        fun vehicleTypeMotorcycleWithPlateA(): Motorcycle {
            return VehicleBuilder()
                .withPlate("ATY38E")
                .buildMotorcycle()
        }

        fun tariffOfCar(): Tariff {
            return TariffBuilder()
                .build(vehicleTypeCar())
        }

        fun tariffOfMotorcycleCC150(): Tariff {
            return  TariffBuilder()
                .build(vehicleTypeMotorcycle150())
        }

        fun tariffOfMotorcycleCC750(): Tariff {
            return  TariffBuilder()
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
            tariff.vehicleDepartureDate = 1624266060000 //June 21, 2021, 9:01 a.m
        }

        fun departureVehicleInJuneAtEightPm(tariff: Tariff) {
            tariff.vehicleDepartureDate = 1624305600000 //June 21, 2021, 8:00 p.m
        }
    }
}