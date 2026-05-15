package com.example.budakattu_sante.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val category: String,
    val imageUrl: String,
    val price: Double,
    val mspPrice: Double,
    val stock: Int,
    val supplierFamilyId: String,
    val harvestDate: String,
    val audioDescription: String? = null
)
