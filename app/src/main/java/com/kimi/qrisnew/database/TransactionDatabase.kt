package com.kimi.qrisnew.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kimi.qrisnew.dao.TransactionDatabaseDao
import com.kimi.qrisnew.model.TransactionModel
import com.kimi.qrisnew.utils.DateConverter
import com.kimi.qrisnew.utils.UUIDConverter


@Database(entities = [TransactionModel::class], version = 1)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class TransactionDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDatabaseDao
}