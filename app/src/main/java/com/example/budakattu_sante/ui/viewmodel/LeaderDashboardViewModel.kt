package com.example.budakattu_sante.ui.viewmodel

import androidx.lifecycle.*
import com.example.budakattu_sante.data.local.dao.TransactionDao
import com.example.budakattu_sante.data.local.entity.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import com.example.budakattu_sante.data.repository.TransactionRepository
import kotlinx.coroutines.launch

@HiltViewModel
class LeaderDashboardViewModel @Inject constructor(
    private val repository: TransactionRepository,
    private val transactionDao: com.example.budakattu_sante.data.local.dao.TransactionDao
) : ViewModel() {

    val totalRevenue: LiveData<Double?> = transactionDao.getTotalRevenue().asLiveData()
    val transactionCount: LiveData<Int> = transactionDao.getTransactionCount().asLiveData()
    val recentTransactions: LiveData<List<Transaction>> = repository.getAllTransactions().asLiveData()

    fun prePopulate() {
        viewModelScope.launch {
            repository.prePopulate()
        }
    }
}
