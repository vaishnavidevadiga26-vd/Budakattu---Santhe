package com.example.budakattu_sante.data.repository

import com.example.budakattu_sante.data.local.dao.TransactionDao
import com.example.budakattu_sante.data.local.entity.Transaction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao
) {
    fun getAllTransactions(): Flow<List<Transaction>> = transactionDao.getAllTransactions()

    suspend fun insertTransaction(transaction: Transaction) = transactionDao.insertTransaction(transaction)

    suspend fun prePopulate() {
        val mockTransactions = listOf(
            Transaction(buyerId = "9876543210", buyerName = "John Doe", productId = 1, productName = "Wild Amla", quantity = 2, totalAmount = 300.0, date = "2023-11-01", paymentStatus = "Paid"),
            Transaction(buyerId = "8887776665", buyerName = "Jane Smith", productId = 2, productName = "Forest Honey", quantity = 1, totalAmount = 450.0, date = "2023-11-02", paymentStatus = "Paid"),
            Transaction(buyerId = "9998887776", buyerName = "Bob Wilson", productId = 3, productName = "Bamboo Baskets", quantity = 3, totalAmount = 900.0, date = "2023-11-03", paymentStatus = "Paid")
        )
        mockTransactions.forEach { transactionDao.insertTransaction(it) }
    }
}
