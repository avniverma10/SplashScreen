package com.example.applicationscreens.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicationscreens.api.ApiService
import com.example.applicationscreens.models.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    var loginState by mutableStateOf<LoginState>(LoginState.Idle)
        private set

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginState = LoginState.Loading
            try {
                val response = apiService.login(LoginRequest(email, password))
                Log.d("AVNI12","response is  -->${response}")
                if (!response.isSuccessful) {
                    Log.e("AVNI11", "API Error: ${response.code()} - ${response.message()}")
                    loginState = LoginState.Error("Error: ${response.code()} - ${response.message()}")
                }
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null && loginResponse.success) {
                        loginState = LoginState.Success("Login successful")
                        // Save the token and user info as needed
                        val token = loginResponse.token
                        val user = loginResponse.user
                        // Example: Save token and user info to SharedPreferences or DataStore
                    } else {
                        loginState = LoginState.Error("Login failed: ${loginResponse?.message}")
                    }
                } else {
                    loginState = LoginState.Error(response.message())
                }
            } catch (e: Exception) {
                loginState = LoginState.Error(e.message ?: "Something went wrong")
            }
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val message: String) : LoginState()
    data class Error(val message: String) : LoginState()
}
