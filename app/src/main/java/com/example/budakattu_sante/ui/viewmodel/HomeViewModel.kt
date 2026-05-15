package com.example.budakattu_sante.ui.viewmodel

import androidx.lifecycle.*
import com.example.budakattu_sante.data.local.entity.Product
import com.example.budakattu_sante.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    val allProducts: LiveData<List<Product>> = repository.getAllProducts().asLiveData()

    init {
        viewModelScope.launch {
            // Check if DB is empty, then populate
            // For demo, always populate if empty
            // (In real app, this would be from network)
        }
    }

    fun prePopulate() {
        viewModelScope.launch {
            repository.prePopulate()
        }
    }
}
