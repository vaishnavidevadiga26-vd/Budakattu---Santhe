package com.example.budakattu_sante.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "msp_rates")
data class MSPRate(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val productName: String,
    val governmentMSP: Double,
    val lastUpdated: String
)
