package com.example.budakattu_sante.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budakattu_sante.databinding.FragmentLeaderDashboardBinding
import com.example.budakattu_sante.ui.adapter.TransactionAdapter
import com.example.budakattu_sante.ui.viewmodel.LeaderDashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

import androidx.navigation.fragment.findNavController
import com.example.budakattu_sante.R

@AndroidEntryPoint
class LeaderDashboardFragment : Fragment() {

    private var _binding: FragmentLeaderDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LeaderDashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeaderDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TransactionAdapter()
        binding.rvRecentTransactions.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecentTransactions.adapter = adapter

        viewModel.totalRevenue.observe(viewLifecycleOwner) { revenue ->
            binding.textTotalRevenue.text = "₹${revenue ?: 0.0}"
        }

        viewModel.transactionCount.observe(viewLifecycleOwner) { count ->
            binding.textOrderCount.text = count.toString()
        }

        viewModel.recentTransactions.observe(viewLifecycleOwner) { transactions ->
            if (transactions.isEmpty()) {
                viewModel.prePopulate()
            }
            adapter.submitList(transactions)
        }

        binding.btnAddProduct.setOnClickListener {
            // Logic to add product
        }

        binding.btnViewCustomers.setOnClickListener {
            findNavController().navigate(R.id.customerListFragment)
        }

        binding.btnViewTransactions.setOnClickListener {
            // Logic to view all transactions
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
