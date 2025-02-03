package com.example.applicationscreens.api

import com.example.applicationscreens.models.ContentResponse
import com.example.applicationscreens.models.LoginResponse
import com.example.applicationscreens.models.SplashApiResponse
import com.example.applicationscreens.models.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @GET("android/content")
    suspend fun getContent(): ContentResponse

    @GET("android/logo_path")
    suspend fun getLogo(): SplashApiResponse

    @POST("api/auth/api-login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("auth/api-signup")
    suspend fun signUp(@Body loginRequest: LoginRequest): Response<LoginResponse>
}


