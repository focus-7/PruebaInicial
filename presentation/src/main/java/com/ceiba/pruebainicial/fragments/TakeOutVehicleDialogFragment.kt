package com.ceiba.pruebainicial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ceiba.application.service.TariffVehicleApplicationService
import com.ceiba.domain.aggregate.Tariff
import com.ceiba.pruebainicial.R
import com.ceiba.pruebainicial.databinding.DialogTakeOutVehicleBinding
import com.ceiba.pruebainicial.utils.getFormattedPrice
import com.ceiba.pruebainicial.viewmodel.TariffViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TakeOutVehicleDialogFragment : DialogFragment() {
    private val viewModel: TariffViewModel by viewModels()
    private lateinit var binding: DialogTakeOutVehicleBinding
    private lateinit var tariffOut: Tariff

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
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
        val tariffVehicleApplicationService = TariffVehicleApplicationService()
        tariffVehicleApplicationService.calculateTariffVehicle(tariffOut)

        entryDate.append(" " + tariffOut.getEntryDateString())
        departureDate.append(" " + tariffOut.getDepartureDateString())
        payment.append(" " + tariffOut.getFormattedPrice())
    }


    private fun eventsEntry() {
        with(binding) {
            buttonCancel.setOnClickListener { dismiss() }
            buttonPayment.setOnClickListener {
                viewModel.takeOutVehicle(tariffOut)
                findNavController().navigate(R.id.mainFragment)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        requireDialog().window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

}