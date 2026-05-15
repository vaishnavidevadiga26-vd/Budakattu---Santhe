package com.example.budakattu_sante.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "supply_logs")
data class SupplyLog(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val productId: Long,
    val familyName: String,
    val village: String,
    val quantity: Int,
    val date: String,
    val isSynced: Boolean = false
)
