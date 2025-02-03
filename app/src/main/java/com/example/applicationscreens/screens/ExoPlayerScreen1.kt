//package com.example.applicationscreens.screens
//
//import android.net.Uri
//import android.util.Log
//import android.widget.FrameLayout
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.CardDefaults
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Card
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.platform.LocalContext
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.media3.common.MediaItem
//import androidx.media3.exoplayer.ExoPlayer
//import androidx.media3.ui.PlayerView
//import coil3.compose.AsyncImage
//import com.example.applicationscreens.viewmodel.ExoPlayerViewModel
//
//@Composable
//fun ExoPlayerScreen(viewModel: ExoPlayerViewModel = hiltViewModel()) {
//    Log.d("AVNI99","Inside the Exoplayer Screen")
//    val channels by viewModel.channels.collectAsState()
//    val selectedChannel by viewModel.selectedChannel.collectAsState()
//
//    Column(modifier = Modifier.fillMaxSize().background(Color.Black)) {
//        // Video Player at the top
//        Log.d("AVNI99","video ${selectedChannel!!.videoUrl}")
//        selectedChannel?.let { channel ->
//            Log.d("AVNI99", "video ${channel.videoUrl}")
//            Player(videoUrl = channel.videoUrl)
//        }
//
//        ChannelList(
//            channels = channels,
//            onChannelClick = { channel -> viewModel.setSelectedChannel(channel) }
//        )
//    }
//}
//
//@Composable
//fun Player(videoUrl: String) {
//    val context = LocalContext.current
//    val exoPlayer = remember {
//        ExoPlayer.Builder(context).build()
//    }
//
//    LaunchedEffect(videoUrl) {
//        exoPlayer.setMediaItem(MediaItem.fromUri(Uri.parse(videoUrl)))
//        exoPlayer.prepare()
//        exoPlayer.playWhenReady = true
//    }
//
//    DisposableEffect(Unit) {
//        onDispose {
//            exoPlayer.release()
//        }
//    }
//
//    Box(modifier = Modifier.fillMaxWidth().height(250.dp)) {
//        AndroidView(
//            modifier = Modifier.fillMaxSize(),
//            factory = { ctx ->
//                PlayerView(ctx).apply {
//                    player = exoPlayer
//                    layoutParams = FrameLayout.LayoutParams(
//                        FrameLayout.LayoutParams.MATCH_PARENT,
//                        FrameLayout.LayoutParams.MATCH_PARENT
//                    )
//                }
//            }
//        )
//    }
//}
//
//// Channel List
//@Composable
//fun ChannelList(channels: List<Channel>, onChannelClick: (Channel) -> Unit) {
//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        items(channels) { channel ->
//            ChannelItem(channel = channel, onClick = { onChannelClick(channel) })
//        }
//    }
//}
//
//// Channel Item UI
//@Composable
//fun ChannelItem(channel: Channel, onClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .clickable { onClick() },
//        elevation = CardDefaults.cardElevation(4.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
//    ) {
//        Row(
//            modifier = Modifier.padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            AsyncImage(
//                model = channel.logoUrl,
//                contentDescription = channel.title,
//                modifier = Modifier.size(50.dp)
//            )
//
//            Spacer(modifier = Modifier.width(16.dp))
//
//            Text(text = channel.title, style = MaterialTheme.typography.titleLarge, color = Color.White)
//        }
//    }
//}
//data class Channel(
//    val title: String,
//    val videoUrl: String,
//    val logoUrl: String
//)
