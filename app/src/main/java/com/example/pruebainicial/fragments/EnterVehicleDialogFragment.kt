package com.example.pruebainicial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.entity.Car
import com.example.domain.entity.Motorcycle
import com.example.domain.entity.Vehicle
import com.example.pruebainicial.R
import com.example.pruebainicial.databinding.DialogAddVehicleBinding
import com.example.pruebainicial.utils.*
import com.example.pruebainicial.viewmodel.TariffViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_add_vehicle.*
import kotlinx.android.synthetic.main.loader.*
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
        binding.rGroupVehicle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked == R.id.typeMotorcycle)
                cylinderContainer.show()
            if (isChecked == R.id.typeCar)
                cylinderContainer.hide()
        }
    }

    private fun eventsEntry() = with(binding) {
        btnCancelar.setOnClickListener {
            dismiss()
        }
        btnAgregar.setOnClickListener {
            val c = Calendar.getInstance()
            if (validateData()) {
                saveData(c.timeInMillis, getTypeOfVehicle())
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

    private fun saveData(date: Long, vehicle: Vehicle) {
        viewModel.enterVehicle(date, vehicle).observe(viewLifecycleOwner, { result ->
            loader.showIf { result is Resource.Loading }
            when (result) {
                is Resource.Loading -> {
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