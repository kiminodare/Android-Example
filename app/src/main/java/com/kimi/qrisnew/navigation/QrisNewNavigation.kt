package com.kimi.qrisnew.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kimi.qrisnew.screens.CameraScreen
import com.kimi.qrisnew.screens.LoginScreen
import com.kimi.qrisnew.screens.MainScreen


@ExperimentalComposeUiApi
@Composable
fun QrisNewNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = QrisNewScreens.LoginScreen.name ) {
        composable(QrisNewScreens.LoginScreen.name){
            LoginScreen(navController = navController)
        }
        composable(QrisNewScreens.MainScreen.name){
            MainScreen(navController = navController)
        }
        composable(
            QrisNewScreens.CameraScreen.name,
        ){
            CameraScreen(navController = navController)
        }
    }
}

