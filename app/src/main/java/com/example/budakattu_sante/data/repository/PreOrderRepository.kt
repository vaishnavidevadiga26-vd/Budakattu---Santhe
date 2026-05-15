package com.example.budakattu_sante.data.repository

import com.example.budakattu_sante.data.local.dao.PreOrderDao
import com.example.budakattu_sante.data.local.entity.PreOrder
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreOrderRepository @Inject constructor(
    private val preOrderDao: PreOrderDao
) {
    fun getPreOrdersByBuyer(buyerId: String): Flow<List<PreOrder>> = preOrderDao.getPreOrdersByBuyer(buyerId)

    suspend fun insertPreOrder(preOrder: PreOrder) = preOrderDao.insertPreOrder(preOrder)

    suspend fun deletePreOrder(orderId: Long) = preOrderDao.deletePreOrder(orderId)
}
