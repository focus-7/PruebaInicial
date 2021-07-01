package com.ceiba.pruebainicial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ceiba.domain.aggregate.Tariff
import com.ceiba.domain.service.TariffPerVehicle
import com.ceiba.domain.service.TariffPerVehicleCarService
import com.ceiba.domain.service.TariffPerVehicleMotorcycleService
import com.ceiba.domain.valueobject.VehicleType
import com.ceiba.pruebainicial.R
import com.ceiba.pruebainicial.databinding.DialogTakeOutVehicleBinding
import com.ceiba.pruebainicial.utils.Resource
import com.ceiba.pruebainicial.utils.show
import com.ceiba.pruebainicial.utils.showToast
import com.ceiba.pruebainicial.viewmodel.TariffViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class TakeOutVehicleDialogFragment : DialogFragment() {
    private lateinit var binding: DialogTakeOutVehicleBinding
    private val viewModel: TariffViewModel by viewModels()
    private lateinit var tariffOut: Tariff
    private lateinit var tariffPerVehicleCar: TariffPerVehicle
    private lateinit var tariffPerVehicleMotorcycle: TariffPerVehicle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogTakeOutVehicleBinding.inflate(inflater, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tariffOut = arguments?.get("dataVehicle") as Tariff
        eventsUI()
        eventsEntry()
    }

    private fun eventsUI() = with(binding) {
        val c = Calendar.getInstance()

        if (tariffOut.vehicleType == VehicleType.CAR.type) {
            tariffPerVehicleCar = TariffPerVehicleCarService()
            tariffOut.calculateVehicleTariff(tariffPerVehicleCar, c.timeInMillis)
        } else {
            tariffPerVehicleMotorcycle = TariffPerVehicleMotorcycleService()
            tariffOut.calculateVehicleTariff(tariffPerVehicleMotorcycle, c.timeInMillis)
        }

        plate.append(" " + tariffOut.vehicle.plate)
        entryDate.append(" " + tariffOut.getEntryDateString())
        departureDate.append(" " + tariffOut.getDepartureDateString())
        payment.append(" $" + tariffOut.amount)
    }

    private fun eventsEntry() = with(binding) {
        buttonCancel.setOnClickListener {
            dismiss()
        }
        buttonPayment.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        viewModel.takeOutVehicle(tariffOut).observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.loader.loaderContainer.show()
                }
                is Resource.Success -> {
                    findNavController().navigate(R.id.mainFragment)
                }
                is Resource.Failure -> {
                    showToast("Ocurrió un error al guardar los datos ${result.exception}")
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        requireDialog().window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

}