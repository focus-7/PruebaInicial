package com.ceiba.pruebainicial.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ceiba.pruebainicial.R
import com.ceiba.pruebainicial.adapters.VehicleAdapter
import com.ceiba.pruebainicial.databinding.FragmentListVehiclesBinding
import com.ceiba.pruebainicial.utils.*
import com.ceiba.pruebainicial.viewmodel.TariffViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehicleListingFragment : Fragment() {
    private val viewModel by activityViewModels<TariffViewModel>()
    private lateinit var vehicleAdapter: VehicleAdapter
    private lateinit var binding: FragmentListVehiclesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        vehicleAdapter = VehicleAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentListVehiclesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvVehicles.layoutManager = LinearLayoutManager(requireContext())
        binding.rvVehicles.adapter = vehicleAdapter
        eventsEntry()
        subscribeUI()
    }

    private fun eventsEntry() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    searchDatabase(query)
                }
                return true
            }
        })
        binding.btnAddVehicle.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_vehicleDialog)
        }
    }

    private fun subscribeUI() = with(binding) {
        viewModel.vehicles.observe(viewLifecycleOwner, Observer { result ->
            if (result.isNotEmpty()) {
                vehicleAdapter.setTariffList(result)
            } else {
                emptyContainer.root.show()
                return@Observer
            }
        })

        viewModel.screenState.observe(viewLifecycleOwner, {
            when (it) {
                MainScreenState.LOADING_DATA -> {
                    loader.loaderContainer.show()
                    emptyContainer.root.hide()
                }
                MainScreenState.SHOW_DATA -> {
                    loader.loaderContainer.hide()
                }
                else -> {
                    showToast("Ocurrió un error al traer los datos")
                    emptyContainer.root.hide()
                    loader.loaderContainer.hide()
                }
            }
        })
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        viewModel.searchVehiclesByPlate(searchQuery)
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        binding.emptyContainer.root.hide()
                    }
                    is Resource.Success -> {
                        if (result.data.isEmpty()) {
                            binding.emptyContainer.root.show()
                            binding.rvVehicles.hide()
                            return@Observer
                        }
                        binding.rvVehicles.show()
                        vehicleAdapter.setTariffList(result.data)
                        binding.emptyContainer.root.hide()
                    }
                    is Resource.Failure -> {
                        showToast("Ocurrió un error al traer los datos ${result.exception}")
                    }
                }
            })
    }
}