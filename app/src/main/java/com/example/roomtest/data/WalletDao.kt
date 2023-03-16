package com.example.roomtest.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletDao {

    @Query("SELECT * FROM wallet_table ORDER BY money ASC")
    fun getMoney() : Flow<List<Wallet>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wallet: Wallet)

    @Query("DELETE FROM wallet_table")
    suspend fun deleteAll()

}