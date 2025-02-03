package com.example.applicationscreens.api

import com.example.applicationscreens.models.ContentResponse
import retrofit2.http.GET


interface ApiService {
    @GET("android/content")
    suspend fun getContent(): ContentResponse
}