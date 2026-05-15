package com.example.budakattu_sante.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.budakattu_sante.data.local.dao.*
import com.example.budakattu_sante.data.local.entity.*

@Database(
    entities = [Product::class, SupplyLog::class, PreOrder::class, MSPRate::class, User::class, Transaction::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun supplyLogDao(): SupplyLogDao
    abstract fun preOrderDao(): PreOrderDao
    abstract fun mspRateDao(): MSPRateDao
    abstract fun userDao(): UserDao
    abstract fun transactionDao(): TransactionDao
}
