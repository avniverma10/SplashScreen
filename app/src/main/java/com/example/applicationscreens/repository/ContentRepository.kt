package com.example.applicationscreens.repository

import android.util.Log
import com.example.applicationscreens.api.ApiService
import com.example.applicationscreens.models.ContentResponse
import com.example.applicationscreens.models.SplashApiResponse
import java.util.Collections.emptyList
import javax.inject.Inject


class ContentRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getContent(): ContentResponse {
        Log.d("AVNI99","Contentt in repo is ${apiService.getContent()}")
        return apiService.getContent()
    }

    suspend fun getLogo(): SplashApiResponse {
        Log.d("AVNI99","Inside the repo getLogo")
        return apiService.getLogo()
    }


}

