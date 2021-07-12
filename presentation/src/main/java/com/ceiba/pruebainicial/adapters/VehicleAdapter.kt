package com.ceiba.pruebainicial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.model.Car
import com.ceiba.domain.model.Motorcycle
import com.ceiba.pruebainicial.R
import com.ceiba.pruebainicial.databinding.VehiclesRowBinding


class VehicleAdapter :
    ListAdapter<Tariff, VehicleAdapter.ViewHolderVehicle>(VehicleDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderVehicle {
        return ViewHolderVehicle(
            VehiclesRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holderVehicle: ViewHolderVehicle, position: Int) {
        holderVehicle.bind(getItem(position))
    }

    class ViewHolderVehicle(
        private val binding: VehiclesRowBinding,
    ) : BaseViewHolder<Tariff>(binding.root) {
        override fun bind(tariffs: Tariff) {
            with(binding) {
                tariff = tariffs
                tariffs.vehicle.apply {
                    when (this) {
                        is Car -> isMotorcycle = false
                        is Motorcycle -> {
                            isMotorcycle = true
                            tvCylinderCapacity.text = String.format(
                                root.resources.getString(R.string.txt_cylindercapacity),
                                cylinderCapacity.toString()
                            )
                        }
                    }
                }
                btnTakeOutVehicle.setOnClickListener {
                    it.findNavController()
                        .navigate(
                            R.id.action_mainFragment_to_takeOutVehicleDialogFragment,
                            bundleOf("dataVehicle" to tariffs)
                        )
                }
            }
        }
    }
}

private class VehicleDiffCallBack : DiffUtil.ItemCallback<Tariff>() {
    override fun areItemsTheSame(oldItem: Tariff, newItem: Tariff): Boolean =
        (oldItem.vehicle.plate == newItem.vehicle.plate)

    override fun areContentsTheSame(oldItem: Tariff, newItem: Tariff): Boolean =
        (oldItem.vehicle.plate == newItem.vehicle.plate)

}
