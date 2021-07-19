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
import com.ceiba.domain.model.Car
import com.ceiba.domain.model.Motorcycle
import com.ceiba.domain.model.Vehicle
import com.ceiba.pruebainicial.R
import com.ceiba.pruebainicial.databinding.DialogAddVehicleBinding
import com.ceiba.pruebainicial.utils.hide
import com.ceiba.pruebainicial.utils.show
import com.ceiba.pruebainicial.viewmodel.TariffViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class EnterVehicleDialogFragment : DialogFragment() {
    private lateinit var binding: DialogAddVehicleBinding
    private val viewModel: TariffViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DialogAddVehicleBinding.inflate(inflater, container, false)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventsUI()
        eventsEntry()
    }

    override fun onStart() {
        super.onStart()
        requireDialog().window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun eventsUI() {
        with(binding) {
            radioGroupTypeOfVehicle.setOnCheckedChangeListener { _, isChecked ->
                when (isChecked) {
                    R.id.typeMotorcycle -> cylinderContainer.show()
                    R.id.typeCar -> cylinderContainer.hide()
                }
            }
        }
    }

    private fun eventsEntry() {
        with(binding) {
            buttonCancel.setOnClickListener {
                dismiss()
            }
            buttonAdd.setOnClickListener {
                if (isEntryValid()) {
                    val c = Calendar.getInstance()
                    val tariff = Tariff(c.timeInMillis, getTypeOfVehicle())
                    viewModel.enterVehicle(tariff)
                    findNavController().navigate(R.id.mainFragment)
                } else {
                    showErrorDialog()
                }
            }
        }
    }

    private fun showErrorDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.error))
            .setMessage(resources.getString(R.string.error_data))
            .setPositiveButton(resources.getString(R.string.confirm)) { _, _ -> }
            .show()
    }

    private fun isEntryValid(): Boolean {
        with(binding) {
            return viewModel.isEntryValid(
                plate.text.toString(),
                cylinderCapacity.text.toString(),
                typeMotorcycle.isChecked
            )
        }
    }

    private fun getTypeOfVehicle(): Vehicle {
        with(binding) {
            val plateUpperCase: String = plate.text.toString().toUpperCase(Locale.getDefault())
            return if (typeCar.isChecked) {
                Car(plateUpperCase)
            } else {
                Motorcycle(
                    plateUpperCase,
                    cylinderCapacity.text.toString().toInt()
                )
            }
        }
    }
}