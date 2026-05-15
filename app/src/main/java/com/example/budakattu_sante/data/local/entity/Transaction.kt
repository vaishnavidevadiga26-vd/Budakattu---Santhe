package com.example.budakattu_sante.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val buyerId: String,
    val buyerName: String,
    val productId: Long,
    val productName: String,
    val quantity: Int,
    val totalAmount: Double,
    val date: String,
    val paymentStatus: String // Paid / Pending
)
