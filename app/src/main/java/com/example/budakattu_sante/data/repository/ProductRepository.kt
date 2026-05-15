package com.example.budakattu_sante.data.repository

import com.example.budakattu_sante.data.local.dao.ProductDao
import com.example.budakattu_sante.data.local.entity.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val productDao: ProductDao
) {
    fun getAllProducts(): Flow<List<Product>> = productDao.getAllProducts()

    suspend fun getProductById(id: Long): Product? = productDao.getProductById(id)

    suspend fun insertProducts(products: List<Product>) = productDao.insertProducts(products)

    // Pre-populate with mock data if needed
    suspend fun prePopulate() {
        val mockProducts = listOf(
            Product(name = "Wild Amla", category = "Wild Fruits", imageUrl = "", price = 150.0, mspPrice = 120.0, stock = 50, supplierFamilyId = "Ravi Gond", harvestDate = "Oct 2023"),
            Product(name = "Forest Honey", category = "Honey", imageUrl = "", price = 450.0, mspPrice = 400.0, stock = 20, supplierFamilyId = "Sita Baiga", harvestDate = "Sept 2023"),
            Product(name = "Bamboo Baskets", category = "Crafts", imageUrl = "", price = 300.0, mspPrice = 250.0, stock = 10, supplierFamilyId = "Arjun Korku", harvestDate = "Aug 2023")
        )
        productDao.insertProducts(mockProducts)
    }
}
