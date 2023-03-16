package com.example.roomtest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Wallet::class), version = 1, exportSchema = false)
abstract class WalletRoomDatabase : RoomDatabase() {

    abstract fun walletDao() : WalletDao

    private class WalletDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var walletDao = database.walletDao()

                    walletDao.deleteAll()

                    var wallet = Wallet(50000,25000)
                    walletDao.insert(wallet)

                    wallet = Wallet(80000,60000)
                    walletDao.insert(wallet)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE : WalletRoomDatabase ? = null

        fun getDatabase(context: Context, scope: CoroutineScope): WalletRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WalletRoomDatabase::class.java,
                    "wallet_database"
                )
                    .addCallback(WalletDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}