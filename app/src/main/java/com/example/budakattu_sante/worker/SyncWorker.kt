package com.example.budakattu_sante.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.budakattu_sante.data.local.dao.SupplyLogDao
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val supplyLogDao: SupplyLogDao
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val unsyncedLogs = supplyLogDao.getUnsyncedLogs()
        if (unsyncedLogs.isEmpty()) return Result.success()

        return try {
            // Mock network call
            unsyncedLogs.forEach { log ->
                // api.syncLog(log)
                supplyLogDao.updateLog(log.copy(isSynced = true))
            }
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}
