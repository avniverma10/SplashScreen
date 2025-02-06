package com.example.applicationscreens.api

import com.example.applicationscreens.models.ContentResponse
import com.example.applicationscreens.models.UserResponse
import com.example.applicationscreens.models.SplashApiResponse
import com.example.applicationscreens.models.LoginRequest
import com.example.applicationscreens.models.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @GET("android/content")
    suspend fun getContent(): ContentResponse

    @GET("android/logo_path")
    suspend fun getLogo(): SplashApiResponse

    @POST("auth/api-login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<UserResponse>

    @POST("auth/api-signup")
    suspend fun signUp(@Body signupRequest: SignUpRequest): Response<UserResponse>
}


