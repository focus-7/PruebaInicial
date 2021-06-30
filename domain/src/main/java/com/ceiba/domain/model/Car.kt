package com.ceiba.domain.model

import com.ceiba.domain.valueobject.VehicleType

class Car(plate: String) : Vehicle(plate, VehicleType.CAR)