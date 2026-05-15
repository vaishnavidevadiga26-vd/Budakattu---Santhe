package com.example.budakattu_sante.data.local.dao

import androidx.room.*
import com.example.budakattu_sante.data.local.entity.PreOrder
import kotlinx.coroutines.flow.Flow

@Dao
interface PreOrderDao {
    @Query("SELECT * FROM pre_orders WHERE buyerId = :buyerId ORDER BY orderDate DESC")
    fun getPreOrdersByBuyer(buyerId: String): Flow<List<PreOrder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPreOrder(preOrder: PreOrder)

    @Update
    suspend fun updatePreOrder(preOrder: PreOrder)

    @Query("DELETE FROM pre_orders WHERE id = :orderId")
    suspend fun deletePreOrder(orderId: Long)
}
