package com.example.roomtest.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class WalletRepository(private val walletDao: WalletDao) {

    val allWallets: Flow<List<Wallet>> = walletDao.getMoney()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(wallet: Wallet) {
        walletDao.insert(wallet)
    }
}