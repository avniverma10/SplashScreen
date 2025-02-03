package com.example.applicationscreens.screens


import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


import com.example.applicationscreens.viewmodel.SplashViewModel
import kotlinx.coroutines.delay

import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage

//import coil.compose.AsyncImage


@Composable
fun SplashScreen(navController: NavController,viewModel: SplashViewModel = hiltViewModel()) {
    val logoUrl by viewModel.logoUrl.collectAsState()

    Log.d("AVNI", "Logo URL: $logoUrl")

    // Delay before navigating to Home Screen
    LaunchedEffect(Unit) {
        delay(3000) // Show splash for 3 seconds

        navController.navigate("login_screen") {
            popUpTo("splash_screen") { inclusive = true } // Remove splash from backstack
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)  // Default background while loading
    ) {
        // Logo
        if (logoUrl!= null) {
            AsyncImage(
                model = logoUrl,
                contentDescription = "Logo",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(250.dp)
            )
        } else {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.White
            )
        }
    }
}
