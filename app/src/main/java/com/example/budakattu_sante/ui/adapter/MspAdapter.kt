package com.example.budakattu_sante.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budakattu_sante.data.local.entity.MSPRate
import com.example.budakattu_sante.databinding.ItemMspBinding

class MspAdapter : ListAdapter<MSPRate, MspAdapter.MspViewHolder>(MspDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MspViewHolder {
        val binding = ItemMspBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MspViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MspViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MspViewHolder(private val binding: ItemMspBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(rate: MSPRate) {
            binding.textProductName.text = rate.productName
            binding.textMspValue.text = "Govt MSP: ₹${rate.governmentMSP}"
            binding.textUpdated.text = "Last updated: ${rate.lastUpdated}"
        }
    }

    class MspDiffCallback : DiffUtil.ItemCallback<MSPRate>() {
        override fun areItemsTheSame(oldItem: MSPRate, newItem: MSPRate): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: MSPRate, newItem: MSPRate): Boolean = oldItem == newItem
    }
}
