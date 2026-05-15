package com.example.budakattu_sante.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budakattu_sante.databinding.FragmentCustomerListBinding
import com.example.budakattu_sante.ui.adapter.Customer
import com.example.budakattu_sante.ui.adapter.CustomerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerListFragment : Fragment() {

    private var _binding: FragmentCustomerListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CustomerAdapter()
        binding.rvCustomers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCustomers.adapter = adapter

        // Mock data
        val customers = listOf(
            Customer("John Doe", "9876543210", 5),
            Customer("Jane Smith", "8887776665", 3),
            Customer("Bob Wilson", "9998887776", 12)
        )
        adapter.submitList(customers)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
