package com.example.budakattu_sante.data.local.dao

import androidx.room.*
import com.example.budakattu_sante.data.local.entity.SupplyLog
import kotlinx.coroutines.flow.Flow

@Dao
interface SupplyLogDao {
    @Query("SELECT * FROM supply_logs ORDER BY date DESC")
    fun getAllLogs(): Flow<List<SupplyLog>>

    @Query("SELECT * FROM supply_logs WHERE isSynced = 0")
    suspend fun getUnsyncedLogs(): List<SupplyLog>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: SupplyLog)

    @Update
    suspend fun updateLog(log: SupplyLog)
}
