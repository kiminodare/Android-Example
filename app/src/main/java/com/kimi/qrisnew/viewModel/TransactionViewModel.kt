package com.kimi.qrisnew.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimi.qrisnew.model.DataOrException
import com.kimi.qrisnew.model.TransactionModel
import com.kimi.qrisnew.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {

    val transactionList2 : MutableState<DataOrException<List<TransactionModel>, Boolean, Exception>> =
        mutableStateOf(
            DataOrException(null, true, Exception(""))
        )

    init {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getAllTransaction().distinctUntilChanged()
                .collect { listOfTransaction ->
                    withContext(Dispatchers.Main) {
                        if (listOfTransaction.isNullOrEmpty()) {
                            transactionList2.value.isLoading = true
                            Log.d("Empty", ": Empty list")
                        } else {
                            transactionList2.value.isLoading = false
                            transactionList2.value.data = listOfTransaction
                        }
                    }
                }
        }
    }

    fun addTransactionList(transaction: TransactionModel) = CoroutineScope(Dispatchers.IO).launch {
        try {
            repository.insert(transaction)
        } catch (e: Exception) {
            Log.d("TransactionViewModel", "addTransactionList: ${e.message}")
        }
    }

    fun deleteAllTransaction() = viewModelScope.launch { repository.deleteAllTransaction() }

    fun deleteTransaction(transaction: TransactionModel) =
        viewModelScope.launch { repository.deleteTransaction(transaction) }
}