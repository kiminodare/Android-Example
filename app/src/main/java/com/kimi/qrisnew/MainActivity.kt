package com.kimi.qrisnew

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kimi.qrisnew.components.Dashboard
import com.kimi.qrisnew.navigation.QrisNewNavigation
import com.kimi.qrisnew.ui.theme.QrisNewTheme
import com.kimi.qrisnew.viewModel.TransactionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QrisNewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QrisNewNavigation()
                    TransactionMainView(viewModel = hiltViewModel())
                }
            }
        }
    }
}

@Composable
fun TransactionMainView(viewModel: TransactionViewModel) {

    if (viewModel.transactionList2.value.isLoading == false) {
        Log.d("TransactionMainView", "TransactionMainView: ${viewModel.transactionList2.value.data}")
    } else {
        Log.d("TransactionMainView", "TransactionMainView: ${viewModel.transactionList2.value.isLoading}")
        Log.d("TransactionMainView", "TransactionMainView: Empty")
    }
}