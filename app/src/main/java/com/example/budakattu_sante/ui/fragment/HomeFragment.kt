package com.example.budakattu_sante.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.budakattu_sante.R
import com.example.budakattu_sante.databinding.FragmentHomeBinding
import com.example.budakattu_sante.ui.adapter.ProductAdapter
import com.example.budakattu_sante.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var preferenceManager: com.example.budakattu_sante.util.PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (preferenceManager.getUserRole() == "Leader") {
            binding.fabAddProduct.visibility = View.VISIBLE
        }

        binding.fabAddProduct.setOnClickListener {
            // Show add product dialog or navigate to add screen
            Toast.makeText(requireContext(), "Opening Leader Dashboard", Toast.LENGTH_SHORT).show()
        }

        val adapter = ProductAdapter { product ->
            val bundle = Bundle().apply {
                putLong("productId", product.id)
            }
            findNavController().navigate(R.id.action_home_to_productDetail, bundle)
        }

        binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProducts.adapter = adapter

        viewModel.allProducts.observe(viewLifecycleOwner) { products ->
            if (products.isEmpty()) {
                viewModel.prePopulate()
            }
            adapter.submitList(products)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
