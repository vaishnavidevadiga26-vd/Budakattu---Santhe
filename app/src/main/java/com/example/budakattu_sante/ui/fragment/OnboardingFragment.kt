package com.example.budakattu_sante.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.budakattu_sante.R
import com.example.budakattu_sante.databinding.FragmentOnboardingBinding
import com.example.budakattu_sante.ui.adapter.OnboardingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = listOf(
            getString(R.string.browse_forest_products),
            getString(R.string.pre_order_before_harvest),
            getString(R.string.support_tribal_families)
        )

        val adapter = OnboardingAdapter(items)
        binding.viewPager.adapter = adapter

        binding.btnNext.setOnClickListener {
            if (binding.viewPager.currentItem < items.size - 1) {
                binding.viewPager.currentItem += 1
            } else {
                findNavController().navigate(R.id.action_onboarding_to_login)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
