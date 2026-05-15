package com.example.budakattu_sante.di

import android.content.Context
import androidx.room.Room
import com.example.budakattu_sante.data.local.AppDatabase
import com.example.budakattu_sante.data.local.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "budakattu_sante_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideProductDao(db: AppDatabase): ProductDao = db.productDao()

    @Provides
    fun provideSupplyLogDao(db: AppDatabase): SupplyLogDao = db.supplyLogDao()

    @Provides
    fun providePreOrderDao(db: AppDatabase): PreOrderDao = db.preOrderDao()

    @Provides
    fun provideMspRateDao(db: AppDatabase): MSPRateDao = db.mspRateDao()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    fun provideTransactionDao(db: AppDatabase): TransactionDao = db.transactionDao()
}
