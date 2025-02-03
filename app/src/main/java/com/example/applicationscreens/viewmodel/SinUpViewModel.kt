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
class SignUpViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    var signUpState by mutableStateOf<SignUpState>(SignUpState.Idle)
        private set

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            signUpState = SignUpState.Loading
            try {
                val response = apiService.signUp(LoginRequest(email, password))
                Log.d("SignUp", "Response: $response")
                if (!response.isSuccessful) {
                    Log.e("SignUp", "API Error: ${response.code()} - ${response.message()}")
                    signUpState = SignUpState.Error("Error: ${response.code()} - ${response.message()}")
                }
                if (response.isSuccessful) {
                    val signUpResponse = response.body()
                    if (signUpResponse != null && signUpResponse.success) {
                        signUpState = SignUpState.Success("Sign up successful")
                        // Save the token or navigate accordingly
                    } else {
                        signUpState = SignUpState.Error("Sign up failed: ${signUpResponse?.message}")
                    }
                } else {
                    signUpState = SignUpState.Error(response.message())
                }
            } catch (e: Exception) {
                signUpState = SignUpState.Error(e.message ?: "Something went wrong")
            }
        }
    }
}

sealed class SignUpState {
    object Idle : SignUpState()
    object Loading : SignUpState()
    data class Success(val message: String) : SignUpState()
    data class Error(val message: String) : SignUpState()
}
