package com.example.budakattu_sante.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.budakattu_sante.R
import com.example.budakattu_sante.databinding.FragmentProfileBinding
import com.example.budakattu_sante.util.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val role = preferenceManager.getUserRole()
        binding.textName.text = preferenceManager.getUserId() ?: "User"
        binding.textRole.text = role ?: "Buyer"

        if (role == "Leader") {
            binding.btnDashboard.visibility = View.VISIBLE
        }

        binding.btnDashboard.setOnClickListener {
            findNavController().navigate(R.id.leaderDashboardFragment)
        }

        binding.btnSettings.setOnClickListener {
            findNavController().navigate(R.id.settingsFragment)
        }

        binding.btnLogout.setOnClickListener {
            androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes") { _, _ ->
                    preferenceManager.clear()
                    findNavController().navigate(R.id.loginFragment)
                }
                .setNegativeButton("No", null)
                .show()
        }

        binding.btnLanguage.setOnClickListener {
            // Logic to toggle language
            // requireActivity().recreate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
