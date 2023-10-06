package com.kimi.qrisnew.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "transaction_tbl")
data class TransactionModel(

    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "transaction_bank")
    val transactionBank: String,

    @ColumnInfo(name = "transaction_id")
    val transactionId: String,

    @ColumnInfo(name = "transaction_merchant")
    val transactionMerchant: String,

    @ColumnInfo(name = "transaction_amount")
    val transactionAmount: String,

    @ColumnInfo(name = "transaction_date")
    val transactionDate: String,
)