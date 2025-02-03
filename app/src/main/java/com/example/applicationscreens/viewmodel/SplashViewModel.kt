package com.example.applicationscreens.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicationscreens.models.ContentResponse
import com.example.applicationscreens.models.SplashApiResponse
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




    init {
        fetchSplashContent()
    }

    private fun fetchSplashContent() {
        viewModelScope.launch {
            try {

                val splashApiResponse = repository.getLogo()
                if (splashApiResponse.success) {
                    _logoUrl.value = splashApiResponse.logo
                } else {
                    Log.e("AVNI", "Failed to fetch logo: Response not successful")
                }
            } catch (e: Exception) {
                Log.e("AVNI", "Failed to fetch splash content", e)
            }
        }
    }
}
