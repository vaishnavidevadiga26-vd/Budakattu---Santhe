package com.example.budakattu_sante.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budakattu_sante.data.repository.PreOrderRepository
import com.example.budakattu_sante.databinding.FragmentPreOrdersBinding
import com.example.budakattu_sante.ui.adapter.PreOrderAdapter
import com.example.budakattu_sante.util.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PreOrdersFragment : Fragment() {

    private var _binding: FragmentPreOrdersBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var repository: PreOrderRepository
    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPreOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PreOrderAdapter()
        binding.rvPreOrders.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPreOrders.adapter = adapter

        val userId = preferenceManager.getUserId() ?: ""
        lifecycleScope.launch {
            repository.getPreOrdersByBuyer(userId).collect { orders ->
                adapter.submitList(orders)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
