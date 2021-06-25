package com.example.pruebainicial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.aggregate.Tariff
import com.example.pruebainicial.R
import com.example.pruebainicial.databinding.DialogTakeOutVehicleBinding
import com.example.pruebainicial.utils.Resource
import com.example.pruebainicial.utils.show
import com.example.pruebainicial.utils.showToast
import com.example.pruebainicial.viewmodel.TariffViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.loader.*
import java.util.*

@AndroidEntryPoint
class TakeOutVehicleDialogFragment : DialogFragment() {
    private lateinit var binding: DialogTakeOutVehicleBinding
    private val viewModel: TariffViewModel by viewModels()
    private lateinit var tariffOut: Tariff

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
        tariffOut.departureDate = c.timeInMillis

        plate.append(" " + tariffOut.vehicle.plate)
        entryDate.append(" " + tariffOut.getEntryDateString())
        departureDate.append(" " + tariffOut.getDepartureDateString())
        payment.append(" $" + tariffOut.amount)
    }

    private fun eventsEntry() = with(binding) {
        btnCancelar.setOnClickListener {
            dismiss()
        }
        btnPayment.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        viewModel.takeOutVehicle(tariffOut).observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    loader.show()
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