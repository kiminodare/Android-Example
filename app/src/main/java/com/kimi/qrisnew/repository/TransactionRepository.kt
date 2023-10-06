package com.kimi.qrisnew.repository

import com.kimi.qrisnew.dao.TransactionDatabaseDao
import com.kimi.qrisnew.model.TransactionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class TransactionRepository @Inject constructor(
    private val transactionDatabaseDao: TransactionDatabaseDao
) {

        fun getAllTransaction() : Flow<List<TransactionModel>> = transactionDatabaseDao.getAllTransaction().flowOn(
            Dispatchers.IO)
            .conflate()

        fun getTransactionById(id: String) : Flow<TransactionModel> = transactionDatabaseDao.getTransactionById(id).flowOn(
            Dispatchers.IO)
            .conflate()

        fun getTransactionByMerchant(merchant: String) : Flow<List<TransactionModel>> = transactionDatabaseDao.getTransactionByMerchant(merchant).flowOn(
            Dispatchers.IO)
            .conflate()

        fun getTransactionByBank(bank: String) : Flow<List<TransactionModel>> = transactionDatabaseDao.getTransactionByBank(bank).flowOn(
            Dispatchers.IO)
            .conflate()

        suspend fun insert(transaction: TransactionModel) = transactionDatabaseDao.insert(transaction)

        suspend fun deleteAllTransaction() = transactionDatabaseDao.deleteAllTransaction()

        suspend fun deleteTransaction(transaction: TransactionModel) = transactionDatabaseDao.deleteTransaction(transaction)
}