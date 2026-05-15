package com.example.budakattu_sante.data.local.dao

import androidx.room.*
import com.example.budakattu_sante.data.local.entity.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun getAllTransactions(): Flow<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    @Query("SELECT SUM(totalAmount) FROM transactions")
    fun getTotalRevenue(): Flow<Double?>

    @Query("SELECT COUNT(*) FROM transactions")
    fun getTransactionCount(): Flow<Int>
}
