package com.ceiba.pruebainicial.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.model.Car
import com.ceiba.domain.model.Motorcycle
import com.ceiba.pruebainicial.R
import com.ceiba.pruebainicial.databinding.VehiclesRowBinding


class VehicleAdapter(private val context: Context) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    private var tariffList = listOf<Tariff>()

    fun setTariffList(tariffList: List<Tariff>) {
        this.tariffList = tariffList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = VehiclesRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return MainViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(tariffList[position])
        }
    }

    override fun getItemCount(): Int {
        return tariffList.size
    }

    private inner class MainViewHolder(
        val binding: VehiclesRowBinding
    ) : BaseViewHolder<Tariff>(binding.root) {
        override fun bind(item: Tariff) = with(binding) {
            tariff = item

            item.vehicle.apply {
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
                        bundleOf("dataVehicle" to item)
                    )
            }
        }
    }
}
