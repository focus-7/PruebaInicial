package com.example.pruebainicial.adapters

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.domain.entity.Car
import com.example.domain.entity.Vehicle
import com.example.pruebainicial.R

@BindingAdapter("isVisible")
fun bindIsVisible(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("changeImageVehicle")
fun changeImageVehicle(imgView: AppCompatImageView, vehicle: Vehicle?) {
    vehicle?.also {
        val img = when (vehicle) {
            is Car -> R.drawable.ic_baseline_directions_car_24
            else -> R.drawable.ic_baseline_sports_motorsports_24
        }
        imgView.setImageDrawable(ContextCompat.getDrawable(imgView.context, img))
    }
}

