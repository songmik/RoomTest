package com.example.roomtest

import android.app.Application
import com.example.roomtest.data.WalletRepository
import com.example.roomtest.data.WalletRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WalletApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { WalletRoomDatabase.getDatabase(this, applicationScope)  }
    val repository by lazy { WalletRepository(database.walletDao()) }
}