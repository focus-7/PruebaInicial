package com.example.pruebainicial.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebainicial.R
import com.example.pruebainicial.adapters.VehicleAdapter
import com.example.pruebainicial.databinding.FragmentMainBinding
import com.example.pruebainicial.utils.*
import com.example.pruebainicial.viewmodel.TariffViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel by activityViewModels<TariffViewModel>()
    private lateinit var vehicleAdapter: VehicleAdapter
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        vehicleAdapter = VehicleAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
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
        viewModel.getVehicles().observe(viewLifecycleOwner, Observer { result ->
            loader.loaderContainer.showIf { result is Resource.Loading }
            when (result) {
                is Resource.Loading -> {
                    emptyContainer.root.hide()
                }
                is Resource.Success -> {
                    if (result.data.isEmpty()) {
                        emptyContainer.root.show()
                        return@Observer
                    }
                    vehicleAdapter.setTariffList(result.data)
                    emptyContainer.root.hide()
                }
                is Resource.Failure -> {
                    showToast("Ocurrió un error al traer los datos ${result.exception}")
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