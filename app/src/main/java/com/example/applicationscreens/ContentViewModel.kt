package com.example.applicationscreens

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
class ContentViewModel @Inject constructor(private val repository: ContentRepository) : ViewModel() {

    private val _contentState = MutableStateFlow<ContentResponse?>(null)
    val contentState: StateFlow<ContentResponse?> = _contentState

    init {
        fetchContent()
    }

    private fun fetchContent() {
        viewModelScope.launch {
            try {
                val response = repository.getContent()
                _contentState.value = response
                Log.d("AVNI", "API Success: $response")
            } catch (e: Exception) {
                Log.e("AVNI", "Failed to fetch data", e)
            }
        }
    }
}
