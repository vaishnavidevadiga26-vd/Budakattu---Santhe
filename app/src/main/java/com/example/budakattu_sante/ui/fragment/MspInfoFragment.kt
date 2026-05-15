package com.example.budakattu_sante.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budakattu_sante.data.local.dao.MSPRateDao
import com.example.budakattu_sante.data.local.entity.MSPRate
import com.example.budakattu_sante.databinding.FragmentMspInfoBinding
import com.example.budakattu_sante.ui.adapter.MspAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MspInfoFragment : Fragment() {

    private var _binding: FragmentMspInfoBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var mspRateDao: MSPRateDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMspInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MspAdapter()
        binding.rvMspInfo.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMspInfo.adapter = adapter

        lifecycleScope.launch {
            mspRateDao.getAllMSPRates().collect { rates ->
                if (rates.isEmpty()) {
                    prePopulateMSP()
                }
                adapter.submitList(rates)
            }
        }
    }

    private suspend fun prePopulateMSP() {
        val mockRates = listOf(
            MSPRate(productName = "Wild Amla", governmentMSP = 120.0, lastUpdated = "2023-10-01"),
            MSPRate(productName = "Forest Honey", governmentMSP = 400.0, lastUpdated = "2023-10-01"),
            MSPRate(productName = "Bamboo", governmentMSP = 50.0, lastUpdated = "2023-10-01")
        )
        mspRateDao.insertMSPRates(mockRates)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
