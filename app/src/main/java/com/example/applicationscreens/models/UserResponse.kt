package com.example.applicationscreens.models

data class UserResponse(
    val success: Boolean,
    val message: String,
    val token: String,
    val user: LoginRequest
)