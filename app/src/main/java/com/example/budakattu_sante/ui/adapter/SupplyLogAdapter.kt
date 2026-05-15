package com.example.budakattu_sante.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budakattu_sante.data.local.entity.SupplyLog
import com.example.budakattu_sante.databinding.ItemSupplyLogBinding

class SupplyLogAdapter : ListAdapter<SupplyLog, SupplyLogAdapter.SupplyLogViewHolder>(SupplyLogDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplyLogViewHolder {
        val binding = ItemSupplyLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SupplyLogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SupplyLogViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SupplyLogViewHolder(private val binding: ItemSupplyLogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(log: SupplyLog) {
            binding.textFamily.text = log.familyName
            binding.textVillage.text = log.village
            binding.textQuantity.text = "Qty: ${log.quantity}"
            binding.textDate.text = log.date
            binding.textSyncStatus.text = if (log.isSynced) "Synced" else "Offline"
        }
    }

    class SupplyLogDiffCallback : DiffUtil.ItemCallback<SupplyLog>() {
        override fun areItemsTheSame(oldItem: SupplyLog, newItem: SupplyLog): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: SupplyLog, newItem: SupplyLog): Boolean = oldItem == newItem
    }
}
