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
import com.ceiba.pruebainicial.utils.Resource
import com.ceiba.pruebainicial.utils.hide
import com.ceiba.pruebainicial.utils.show
import com.ceiba.pruebainicial.utils.showToast
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
        savedInstanceState: Bundle?
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

    private fun eventsUI() {
        binding.radioGroupTypeOfVehicle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked == R.id.typeMotorcycle)
                binding.cylinderContainer.show()
            if (isChecked == R.id.typeCar)
                binding.cylinderContainer.hide()
        }
    }

    private fun eventsEntry() = with(binding) {
        buttonCancel.setOnClickListener {
            dismiss()
        }
        buttonAdd.setOnClickListener {
            val c = Calendar.getInstance()
            if (validateData()) {
                val tariff = Tariff(c.timeInMillis, getTypeOfVehicle())
                saveData(tariff)
            } else {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(resources.getString(R.string.error))
                    .setMessage(resources.getString(R.string.error_data))
                    .setPositiveButton(resources.getString(R.string.confirm)) { _, _ -> }
                    .show()
            }
        }
    }

    private fun validateData(): Boolean = with(binding) {
        return when {
            plate.text.toString().isEmpty() -> false
            (typeMotorcycle.isChecked && cylinderCapacity.text.toString().isEmpty()) -> false
            else -> true
        }
    }

    private fun getTypeOfVehicle(): Vehicle = with(binding) {
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

    private fun saveData(tariff: Tariff) {
        viewModel.enterVehicle(tariff).observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Success -> {
                    findNavController().navigate(R.id.mainFragment)
                }
                is Resource.Failure -> {
                    showToast("Ocurrió un error al guardar los datos ${result.exception}")
                }
                is Resource.Loading -> {
                    binding.loader.loaderContainer.show()
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