package com.ceiba.pruebainicial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ceiba.domain.valueobject.Status
import com.ceiba.pruebainicial.R
import com.ceiba.pruebainicial.adapters.VehicleAdapter
import com.ceiba.pruebainicial.databinding.FragmentListVehiclesBinding
import com.ceiba.pruebainicial.utils.hide
import com.ceiba.pruebainicial.utils.onQueryTextChanged
import com.ceiba.pruebainicial.utils.show
import com.ceiba.pruebainicial.utils.showIf
import com.ceiba.pruebainicial.viewmodel.TariffViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@AndroidEntryPoint
class VehicleListingFragment : Fragment() {
    private val viewModel by activityViewModels<TariffViewModel>()
    private lateinit var vehicleAdapter: VehicleAdapter
    private lateinit var binding: FragmentListVehiclesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        vehicleAdapter = VehicleAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentListVehiclesBinding.inflate(inflater, container, false)
        return binding.root
    }


    @ExperimentalCoroutinesApi
    @FlowPreview
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvVehicles.layoutManager = LinearLayoutManager(requireContext())
        binding.rvVehicles.adapter = vehicleAdapter
        eventsEntry()
        subscribeUi()
    }

    private fun eventsEntry() {
        binding.searchView.onQueryTextChanged {
            viewModel.setSearchPlate(it)
        }

        binding.btnAddVehicle.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_vehicleDialog)
        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun subscribeUi() = with(binding) {
        viewModel.screenState.observe(viewLifecycleOwner, {
            when (it) {
                MainScreenState.LOADING_DATA -> loader.loaderContainer.show()
                MainScreenState.SHOW_DATA -> loader.loaderContainer.hide()
                else -> loader.loaderContainer.hide()
            }
        })

        viewModel.vehicles.observe(viewLifecycleOwner, { result ->
            loader.loaderContainer.showIf { result.status == Status.LOADING }
            emptyContainer.showIf { result.data.isNullOrEmpty() }
            vehicleAdapter.submitList(result.data)
        })

        viewModel.vehiclesByPlate.observe(viewLifecycleOwner, { items ->
            emptyContainer.showIf { items.isEmpty() }
            vehicleAdapter.submitList(items)
        })
    }
}
