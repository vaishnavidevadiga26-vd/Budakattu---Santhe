package com.example.budakattu_sante.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budakattu_sante.data.local.entity.Product
import com.example.budakattu_sante.databinding.ItemProductBinding

class ProductAdapter(private val onClick: (Product) -> Unit) :
    ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.textName.text = product.name
            binding.textPrice.text = "₹${product.price}"
            binding.textMsp.text = "MSP: ₹${product.mspPrice}"
            binding.textStock.text = "Stock: ${product.stock}"
            binding.root.setOnClickListener { onClick(product) }
        }
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem == newItem
    }
}
