//package com.example.applicationscreens.viewmodel
//
//import android.util.Log
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.applicationscreens.repository.ContentRepository
//import com.example.applicationscreens.screens.Channel
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//
//@HiltViewModel
//class ExoPlayerViewModel @Inject constructor(private val repository: ContentRepository) : ViewModel() {
//
//    // StateFlow to hold the list of channels
//    private val _channels = MutableStateFlow<List<Channel>>(emptyList())
//    val channels: StateFlow<List<Channel>> = _channels
//
//    // StateFlow to hold the selected channel
//    private val _selectedChannel = MutableStateFlow<Channel?>(null)
//    val selectedChannel: StateFlow<Channel?> = _selectedChannel
//
//    init {
//
//        Log.d("AVNI99","viewmodel is inistalized")
//        fetchChannels()
//        Log.d("AVNI99","fetch channel was called")
//    }
//
//    // Function to fetch the channels data from repository
//    private fun fetchChannels() {
//
//        Log.d("AVNI99", "Inside the fetch channels was called")
//        viewModelScope.launch {
//            Log.d("AVNI99", "Inside CoroutineScope of fetchChannels()")
//            try {
//                val channelResponse = repository.getContent()
//                Log.d("AVNI99","channel response is -->${channelResponse}")
//                if (channelResponse.success) {
//                    Log.d("AVNI99", "fetchChannels: ifff channelResponse.success")
//                    // Map the Data to Channel
//                    _channels.value = channelResponse.data.map {
//                        Channel(
//                            title = it.title,
//                            videoUrl = it.videoUrl,
//                            logoUrl = it.thumbnailUrl // Using thumbnailUrl as the logo URL
//                        )
//                    }
//                    // Set the first channel as selected by default
//                    _selectedChannel.value = _channels.value.firstOrNull()
//                }
//                else {
//                    Log.d("AVNI99", "fetchChannels: elseeee channelResponse.success")
//                    useDummyData()
//                }
//            } catch (e: Exception) {
//                Log.e("ExoPlayerViewModel", "Failed to fetch channels", e)
//                useDummyData()
//            }
//        }
//    }
//    private fun useDummyData() {
//        _channels.value = listOf(
//            Channel("TV9 Bharatvarsh", "http://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8", "https://img.freepik.com/free-vector/breaking-news-theme_23-2148504237.jpg?t=st=1738577860~exp=1738581460~hmac=adc06c2e61bea14fd0062ad36eddd9726510ced16e2eb5313d9f7fd8ac2bc30c&w=740")
//        )
//        _selectedChannel.value = _channels.value.firstOrNull()
//    }
//
//
//    // Function to update selected channel
//    fun setSelectedChannel(channel: Channel) {
//        _selectedChannel.value = channel
//    }
//}
//
