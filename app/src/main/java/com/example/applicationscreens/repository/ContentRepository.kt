package com.example.applicationscreens.repository

import android.util.Log
import com.example.applicationscreens.api.ApiService
import com.example.applicationscreens.models.ContentResponse
import javax.inject.Inject


class ContentRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getContent(): ContentResponse {
        return apiService.getContent()
    }
}

