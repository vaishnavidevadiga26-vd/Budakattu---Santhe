package com.example.budakattu_sante.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budakattu_sante.data.local.entity.PreOrder
import com.example.budakattu_sante.databinding.ItemPreOrderBinding

class PreOrderAdapter : ListAdapter<PreOrder, PreOrderAdapter.PreOrderViewHolder>(PreOrderDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreOrderViewHolder {
        val binding = ItemPreOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PreOrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PreOrderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PreOrderViewHolder(private val binding: ItemPreOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(order: PreOrder) {
            binding.textOrderId.text = "Order #${order.id}"
            binding.textStatus.text = order.status
            binding.textDate.text = order.orderDate
            binding.textQuantity.text = "Qty: ${order.quantity}"
        }
    }

    class PreOrderDiffCallback : DiffUtil.ItemCallback<PreOrder>() {
        override fun areItemsTheSame(oldItem: PreOrder, newItem: PreOrder): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: PreOrder, newItem: PreOrder): Boolean = oldItem == newItem
    }
}
