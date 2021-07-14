package com.ceiba.pruebainicial.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.model.Motorcycle
import com.ceiba.domain.model.Vehicle
import com.ceiba.domain.valueobject.VehicleType
import com.ceiba.pruebainicial.R

@BindingAdapter("isVisible")
fun bindIsVisible(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("cylinderCapacityFormatted")
fun TextView.setCylinderCapacityFormatted(item: Vehicle) {
    text = when (item) {
        is Motorcycle -> String.format(context.resources.getString(R.string.txt_cylindercapacity),
            item.cylinderCapacity)
        else -> ""
    }
}

@BindingAdapter("vehicleImage")
fun ImageView.setVehicleImage(item: Tariff) {
    setImageResource(when (item.vehicleType) {
        VehicleType.CAR.type -> R.drawable.ic_baseline_directions_car_24
        VehicleType.MOTORCYCLE.type -> R.drawable.ic_baseline_sports_motorsports_24
        else -> R.drawable.ic_baseline_directions_car_24
    })
}

