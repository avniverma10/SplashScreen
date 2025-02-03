package com.example.applicationscreens.screens

import android.util.Log
import androidx.compose.foundation.background
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
import coil3.compose.AsyncImage
import com.example.applicationscreens.R
import com.example.applicationscreens.viewmodel.SplashViewModel
import kotlinx.coroutines.delay

import androidx.compose.foundation.Image

import androidx.compose.ui.res.painterResource





@Composable
fun SplashScreen(viewModel: SplashViewModel = hiltViewModel()) {
    val logoUrl by viewModel.logoUrl.collectAsState()
    val backgroundUrl by viewModel.backgroundUrl.collectAsState()

    // Delay before navigating to Home Screen
    LaunchedEffect(Unit) {
        delay(3000) // Show splash for 3 seconds
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // Default background while loading
    ) {
        // Background Image
        if (backgroundUrl != null) {
            AsyncImage(
                model = backgroundUrl,
                contentDescription = "Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

//        Image(
//            painter = painterResource(id = R.drawable.news),
//            contentDescription = "Sample Logo",
//            modifier = Modifier
//                .align(Alignment.Center)
//                .size(150.dp)
//        )


//        // Logo
        if (logoUrl != null) {
            Log.d("AVNIV","Inside logo")
            AsyncImage(
                model = logoUrl,
                contentDescription = "Logo",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(150.dp)
            )
        } else {
            Log.d("AVNIV","inside else")
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.White
            )
        }
    }
}
