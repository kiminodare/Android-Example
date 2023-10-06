package com.kimi.qrisnew.screens

import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kimi.qrisnew.model.TransactionModel
import com.kimi.qrisnew.navigation.QrisNewScreens
import com.kimi.qrisnew.utils.BarcodeAnalyser
import com.kimi.qrisnew.utils.GeneralUtils
import com.kimi.qrisnew.viewModel.TransactionViewModel
import java.time.LocalDate
import java.util.concurrent.Executors


@Composable
fun CameraScreen(navController: NavHostController) {
    Surface {
        AppContent(navController = navController, viewModel = hiltViewModel())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(
    scaleType: PreviewView.ScaleType = PreviewView.ScaleType.FILL_CENTER,
    cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
    viewModel: TransactionViewModel,
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Camera") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            AndroidView(factory = { context ->
                val cameraExecutor = Executors.newSingleThreadExecutor()
                val previewView = PreviewView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    this.scaleType = scaleType
                }

                val previewViewUseCase = Preview.Builder()
                    .build()

                previewViewUseCase.setSurfaceProvider(previewView.surfaceProvider)

                val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
                cameraProviderFuture.addListener({
                    val imageCapture = ImageCapture.Builder()
                        .build()

                    val imageAnalysis = ImageAnalysis.Builder()
                        .build()
                        .also {
                            it.setAnalyzer(cameraExecutor, BarcodeAnalyser { barcodes ->
                                barcodes.forEach {
                                    var dataAdded = false
                                    it.rawValue?.split(".").let { split ->
                                        try {
                                            val transactionModel = TransactionModel(
                                                transactionBank = split?.get(0) ?: "",
                                                transactionId = split?.get(1) ?: "",
                                                transactionAmount = split?.get(2) ?: "",
                                                transactionMerchant = split?.get(3) ?: "",
                                                transactionDate = LocalDate.now().toString(),
                                            )
                                            viewModel.addTransactionList(transactionModel)
                                            dataAdded = true
                                            navController.navigate(QrisNewScreens.MainScreen.name)
                                        } catch (e: Exception) {
                                            dataAdded = true
                                            Log.d("Error", "AppContent: ${e.message}")
                                            navController.navigate(QrisNewScreens.MainScreen.name)
                                        }
                                    }
                                }
                            })
                        }
                    try {
                        val cameraProvider = cameraProviderFuture.get()
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            (context as ComponentActivity),
                            cameraSelector,
                            previewViewUseCase,
                            imageCapture,
                            imageAnalysis
                        )
                    } catch (exc: Exception) {
                        Log.e("DEBUG", "Use case binding failed", exc)
                        Toast.makeText(context, "Use case binding failed", Toast.LENGTH_SHORT)
                            .show()
                    }
                }, context.mainExecutor)

                previewView
            })
        }
    }
}

