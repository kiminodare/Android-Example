package com.kimi.qrisnew.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kimi.qrisnew.components.Dashboard

//@Preview(showBackground = true)
@Composable
fun MainScreen(
    navController: NavHostController
) {
    Dashboard(hiltViewModel(), navController)
}