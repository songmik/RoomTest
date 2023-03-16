package com.example.roomtest.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallet_table")
data class Wallet(
    @PrimaryKey @ColumnInfo(name = "money")
    val plus: Int,
    val minus: Int
)
