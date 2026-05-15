package com.example.budakattu_sante.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budakattu_sante.databinding.ItemCustomerBinding

data class Customer(val name: String, val phone: String, val orderCount: Int)

class CustomerAdapter : ListAdapter<Customer, CustomerAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCustomerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemCustomerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Customer) {
            binding.textName.text = item.name
            binding.textPhone.text = item.phone
            binding.textOrders.text = "Orders: ${item.orderCount}"
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Customer>() {
        override fun areItemsTheSame(oldItem: Customer, newItem: Customer) = oldItem.phone == newItem.phone
        override fun areContentsTheSame(oldItem: Customer, newItem: Customer) = oldItem == newItem
    }
}
