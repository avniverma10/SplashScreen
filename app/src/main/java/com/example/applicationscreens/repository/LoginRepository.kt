package com.example.applicationscreens.repository

import com.example.applicationscreens.api.ApiService
import com.example.applicationscreens.models.UserResponse
import com.example.applicationscreens.models.LoginRequest
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun login(email: String, password: String): Result<UserResponse> {
        return try {
            // Call the login API
            val response = apiService.login(LoginRequest(email, password))

            // Check if the response is successful
            if (response.isSuccessful) {
                // Return the successful result
                Result.success(response.body()!!)
            } else {
                // Return error if the response is not successful
                Result.failure(Exception("Error: ${response.message()}"))
            }
        } catch (e: Exception) {
            // Return failure in case of an exception (e.g., network failure)
            Result.failure(e)
        }
    }
}
