package com.example.budakattu_sante.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budakattu_sante.data.local.entity.Transaction
import com.example.budakattu_sante.databinding.ItemTransactionBinding

class TransactionAdapter : ListAdapter<Transaction, TransactionAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Transaction) {
            binding.textBuyerName.text = item.buyerName
            binding.textProductName.text = item.productName
            binding.textAmount.text = "₹${item.totalAmount}"
            binding.textDate.text = item.date
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Transaction>() {
        override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction) = oldItem == newItem
    }
}
