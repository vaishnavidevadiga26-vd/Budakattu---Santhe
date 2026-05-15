package com.example.budakattu_sante.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budakattu_sante.data.local.dao.SupplyLogDao
import com.example.budakattu_sante.data.local.entity.SupplyLog
import com.example.budakattu_sante.databinding.FragmentSupplyLogBinding
import com.example.budakattu_sante.ui.adapter.SupplyLogAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class SupplyLogFragment : Fragment() {

    private var _binding: FragmentSupplyLogBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var supplyLogDao: SupplyLogDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSupplyLogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SupplyLogAdapter()
        binding.rvSupplyLogs.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSupplyLogs.adapter = adapter

        lifecycleScope.launch {
            supplyLogDao.getAllLogs().collect { logs ->
                adapter.submitList(logs)
            }
        }

        binding.fabAddLog.setOnClickListener {
            showAddLogDialog()
        }
    }

    private fun showAddLogDialog() {
        val view = LayoutInflater.from(requireContext()).inflate(com.example.budakattu_sante.R.layout.dialog_add_log, null)
        val etFamily = view.findViewById<EditText>(com.example.budakattu_sante.R.id.etFamily)
        val etVillage = view.findViewById<EditText>(com.example.budakattu_sante.R.id.etVillage)
        val etQty = view.findViewById<EditText>(com.example.budakattu_sante.R.id.etQty)

        AlertDialog.Builder(requireContext())
            .setTitle("New Supply Entry")
            .setView(view)
            .setPositiveButton("Save") { _, _ ->
                val family = etFamily.text.toString()
                val village = etVillage.text.toString()
                val qty = etQty.text.toString().toIntOrNull() ?: 0
                if (family.isNotEmpty() && qty > 0) {
                    saveLog(family, village, qty)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun saveLog(family: String, village: String, qty: Int) {
        val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date())
        val log = SupplyLog(productId = 1, familyName = family, village = village, quantity = qty, date = date)
        lifecycleScope.launch {
            supplyLogDao.insertLog(log)
            Toast.makeText(requireContext(), "Log saved offline", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
