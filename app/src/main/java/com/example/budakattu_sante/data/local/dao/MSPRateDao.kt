package com.example.budakattu_sante.data.local.dao

import androidx.room.*
import com.example.budakattu_sante.data.local.entity.MSPRate
import kotlinx.coroutines.flow.Flow

@Dao
interface MSPRateDao {
    @Query("SELECT * FROM msp_rates")
    fun getAllMSPRates(): Flow<List<MSPRate>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMSPRates(rates: List<MSPRate>)
}
