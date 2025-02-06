//package com.example.applicationscreens.repository
//
//import com.example.applicationscreens.api.ApiService
//import com.example.applicationscreens.models.LoginRequest
//import com.example.applicationscreens.models.LoginResponse
//import com.example.applicationscreens.models.SignUpRequest
//import javax.inject.Inject
//
//class SignUpRepository  @Inject constructor(private val apiService: ApiService) {
//    suspend fun signUp(email: String, password: String): LoginResponse? {
//        val response =apiService.signUp(SignUpRequest(email, password))
//        return if (response.isSuccessful) {
//            response.body()
//        } else {
//            null
//        }
//    }
//}