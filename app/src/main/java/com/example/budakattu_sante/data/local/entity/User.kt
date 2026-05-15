package com.example.budakattu_sante.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: String,
    val phone: String,
    val name: String,
    val role: String, // Buyer / Cooperative Leader
    val token: String? = null
)
