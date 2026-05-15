package com.example.budakattu_sante.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pre_orders")
data class PreOrder(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val productId: Long,
    val buyerId: String,
    val quantity: Int,
    val status: String, // Pending / Confirmed / Harvested / Dispatched / Delivered
    val orderDate: String
)
