package com.example.exoplayer1

import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C.CONTENT_TYPE_DASH
import androidx.media3.common.C.CONTENT_TYPE_HLS
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.applicationscreens.R

@Composable
fun ExoPlayerScreen() {
    val context = LocalContext.current
    var selectedChannel by remember { mutableStateOf(sampleChannels.first()) }

    Column(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        // Video Player at the top
        Player(
            context = context,
            videoUrl = selectedChannel.videoUrl
        )

        ChannelList(
            channels = sampleChannels,
            onChannelClick = { selectedChannel = it }
        )
    }
}

@OptIn(UnstableApi::class)
@Composable
fun Player(context: Context, videoUrl: String) {
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build()
    }

    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT

    LaunchedEffect(videoUrl) {
        exoPlayer.setMediaItem(MediaItem.fromUri(Uri.parse(videoUrl)))
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    Box(modifier = Modifier.fillMaxWidth().height(if (isPortrait) 250.dp else 400.dp)) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            }
        )
    }
}

// Channel List
@Composable
fun ChannelList(channels: List<Channel>, onChannelClick: (Channel) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(channels) { channel ->
            ChannelItem(channel = channel, onClick = { onChannelClick(channel) })
        }
    }
}

// Channel Item UI
@Composable
fun ChannelItem(channel: Channel, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = channel.logoResId),
                contentDescription = channel.name,
                modifier = Modifier.size(50.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(text = channel.name, style = MaterialTheme.typography.titleLarge, color = Color.White)
        }
    }
}

data class Channel(val name: String, val videoUrl: String, val logoResId: Int)

val sampleChannels = listOf(
    Channel("Republic Bharat", "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8", R.drawable.news),
    Channel("Aaj Tak", "https://devstreaming-cdn.apple.com/videos/streaming/examples/img_bipbop_adv_example_fmp4/master.m3u8",  R.drawable.news),
    Channel("TV9 Bharatvarsh", "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8", R.drawable.news),
    Channel("India TV", "https://devstreaming-cdn.apple.com/videos/streaming/examples/img_bipbop_adv_example_fmp4/master.m3u8",  R.drawable.news),
    Channel("Republic Bharat", "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8", R.drawable.news),
    Channel("Aaj Tak", "https://devstreaming-cdn.apple.com/videos/streaming/examples/img_bipbop_adv_example_fmp4/master.m3u8",  R.drawable.news),
    Channel("TV9 Bharatvarsh", "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8", R.drawable.news),
    Channel("India TV", "https://devstreaming-cdn.apple.com/videos/streaming/examples/img_bipbop_adv_example_fmp4/master.m3u8",  R.drawable.news),
    Channel("Republic Bharat", "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8", R.drawable.news),
    Channel("Aaj Tak", "https://devstreaming-cdn.apple.com/videos/streaming/examples/img_bipbop_adv_example_fmp4/master.m3u8",  R.drawable.news),
    Channel("TV9 Bharatvarsh", "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8", R.drawable.news),
    Channel("India TV", "https://devstreaming-cdn.apple.com/videos/streaming/examples/img_bipbop_adv_example_fmp4/master.m3u8",  R.drawable.news)
)
