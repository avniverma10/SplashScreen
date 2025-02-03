package com.example.applicationscreens.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicationscreens.models.ContentResponse
import com.example.applicationscreens.repository.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: ContentRepository) : ViewModel() {

    private val _logoUrl = MutableStateFlow<String?>(null)
    val logoUrl: StateFlow<String?> = _logoUrl

    private val _backgroundUrl = MutableStateFlow<String?>(null)
    val backgroundUrl: StateFlow<String?> = _backgroundUrl

    init {
        fetchSplashContent()
    }

    private fun fetchSplashContent() {
        viewModelScope.launch {
            try {
                val response = repository.getContent()
                if (response.success && response.data.isNotEmpty()) {
                    _logoUrl.value = response.data[0].thumbnailUrl  // Assuming this is the logo
//                    _backgroundUrl.value = response.data[0].videoUrl  // Replace with actual background field
                }
            } catch (e: Exception) {
                Log.e("AVNI", "Failed to fetch splash content", e)
            }
        }
    }
}
