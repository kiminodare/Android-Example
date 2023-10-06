package com.kimi.qrisnew.utils

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

class BarcodeAnalyser(
    val onBarcodeDetected: (List<Barcode>) -> Unit
) : ImageAnalysis.Analyzer {

    val detectedBarcodes = mutableListOf<Barcode>()
    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val option = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE,
                Barcode.FORMAT_AZTEC
            )
            .build()
        val scanner = com.google.mlkit.vision.barcode.BarcodeScanning.getClient(option)
        val mediaImage = imageProxy.image
        mediaImage?.let {
            val image = InputImage.fromMediaImage(it, imageProxy.imageInfo.rotationDegrees)
            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    if (barcodes.isNotEmpty()) {
                        detectedBarcodes.addAll(barcodes)
                        onBarcodeDetected(detectedBarcodes)
                    }
                }
                .addOnFailureListener {
                    Log.d("BarcodeAnalyser", "analyze: ${it.message}")
                }
        }
        imageProxy.close()
    }
}