package com.example.applicationscreens.navigation


import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applicationscreens.screens.SignUpScreen
import com.example.applicationscreens.screens.SplashScreen
import com.example.exoplayer1.ExoPlayerScreen


@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable(route = "splash_screen") {
            SplashScreen(navController)
        }
        composable("login_screen") {
            LoginScreen(navController)
        }
        composable("exoplayer") {
            ExoPlayerScreen()  // Your ExoPlayerScreen Composable
        }
        composable("signup") {
            SignUpScreen(navController)
        }
    }
}