package com.ceiba.pruebainicial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ceiba.domain.aggregate.Tariff
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
        val item = getItem(position)
        holderVehicle.bind(item)
    }

    class ViewHolderVehicle(private val binding: VehiclesRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tariffs: Tariff) {
            binding.apply {
                tariff = tariffs

                btnTakeOutVehicle.setOnClickListener {
                    it.findNavController()
                        .navigate(
                            R.id.action_mainFragment_to_takeOutVehicleDialogFragment,
                            bundleOf("dataVehicle" to tariffs)
                        )
                }
                executePendingBindings()
            }
        }
    }
}

private class VehicleDiffCallBack : DiffUtil.ItemCallback<Tariff>() {

    override fun areItemsTheSame(oldItem: Tariff, newItem: Tariff): Boolean =
        oldItem.vehicle.plate == newItem.vehicle.plate


    override fun areContentsTheSame(oldItem: Tariff, newItem: Tariff): Boolean =
        oldItem.vehicle.plate == newItem.vehicle.plate
}
