package com.example.budakattu_sante.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.budakattu_sante.R
import com.example.budakattu_sante.databinding.FragmentLoginBinding
import com.example.budakattu_sante.util.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var preferenceManager: PreferenceManager

    private var isOtpSent = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val phone = binding.etPhone.text.toString()
            if (phone.isEmpty()) {
                Toast.makeText(requireContext(), "Enter phone number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isOtpSent) {
                // Mock sending OTP
                isOtpSent = true
                binding.otpLayout.visibility = View.VISIBLE
                binding.btnLogin.text = "Verify OTP"
                Toast.makeText(requireContext(), "OTP sent: 1234", Toast.LENGTH_LONG).show()
            } else {
                val otp = binding.etOtp.text.toString()
                if (otp == "1234") {
                    val role = if (binding.radioBuyer.isChecked) "Buyer" else "Leader"
                    preferenceManager.saveUser(phone, role)
                    findNavController().navigate(R.id.action_login_to_home)
                } else {
                    Toast.makeText(requireContext(), "Invalid OTP", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
