package com.kimi.qrisnew.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kimi.qrisnew.model.TransactionModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDatabaseDao {

    @Query("SELECT * FROM transaction_tbl")
    fun getAllTransaction(): Flow<List<TransactionModel>>

    @Query("SELECT * FROM transaction_tbl WHERE transaction_id = :id")
    fun getTransactionById(id: String): Flow<TransactionModel>

    @Query("SELECT * FROM transaction_tbl WHERE transaction_merchant = :merchant")
    fun getTransactionByMerchant(merchant: String): Flow<List<TransactionModel>>

    @Query("SELECT * FROM transaction_tbl WHERE transaction_bank = :bank")
    fun getTransactionByBank(bank: String): Flow<List<TransactionModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transaction: TransactionModel)

    @Query("DELETE FROM transaction_tbl")
    fun deleteAllTransaction()

    @Delete
    fun deleteTransaction(transaction: TransactionModel)
}