package com.example.budakattu_sante.ui.fragment

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.budakattu_sante.data.local.entity.Product
import com.example.budakattu_sante.data.repository.ProductRepository
import com.example.budakattu_sante.databinding.FragmentProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailFragment : Fragment(), TextToSpeech.OnInitListener {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var repository: ProductRepository

    private var tts: TextToSpeech? = null
    private var currentProduct: Product? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tts = TextToSpeech(requireContext(), this)

        val productId = arguments?.getLong("productId") ?: -1L
        if (productId != -1L) {
            lifecycleScope.launch {
                currentProduct = repository.getProductById(productId)
                currentProduct?.let { bind(it) }
            }
        }

        binding.btnAudio.setOnClickListener {
            currentProduct?.let {
                val text = "${it.name}. Produced by ${it.supplierFamilyId}. Price is ${it.price} rupees. Current stock is ${it.stock} units."
                tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }

        binding.btnPreOrder.setOnClickListener {
            val qty = binding.etQuantity.text.toString().toIntOrNull() ?: 1
            val stock = currentProduct?.stock ?: 0
            if (qty > stock) {
                showStockLimitDialog(stock)
            } else {
                Toast.makeText(requireContext(), "Added to Pre-Orders", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bind(product: Product) {
        binding.textName.text = product.name
        binding.textSupplier.text = "By: ${product.supplierFamilyId}"
        binding.textPrice.text = "₹${product.price}"
        binding.textStock.text = "In Stock: ${product.stock}"
    }

    private fun showStockLimitDialog(maxStock: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle("Stock Limit")
            .setMessage("Only $maxStock units available. Adjust quantity?")
            .setPositiveButton("OK") { _, _ ->
                binding.etQuantity.setText(maxStock.toString())
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale.US
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tts?.stop()
        tts?.shutdown()
        _binding = null
    }
}
