import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.applicationscreens.viewmodel.LoginState
import com.example.applicationscreens.viewmodel.LoginViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    Log.d("AVNI11","Inside the login screen")

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isButtonEnabled = email.isNotBlank() && password.isNotBlank()

    val loginState = viewModel.loginState

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.9f),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Login",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Email TextField
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email Address") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Password TextField
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Login Button
                Button(
                    onClick = { viewModel.login(email, password) },
                    enabled = isButtonEnabled,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF9C89F6))
                ) {
                    Text(text = "LOGIN")
                }

                // Loading Indicator
                if (loginState is LoginState.Loading) {
                    CircularProgressIndicator()
                }

                // Error Message
                if (loginState is LoginState.Error) {
                    Text(text = "Error: ${loginState.message}", color = Color.Red)
                }

                LaunchedEffect(loginState) {
                    if (loginState is LoginState.Success) {
                        Log.d("AVNI11", "Login is successful")
                        navController.navigate("exoplayer")
                        Log.d("AVNI11", "Navigated to exoplayer")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // "Don't have an account? Sign up"
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Don't have an account? ")

                    Text(
                        text = "Sign up",
                        color = Color.Blue,
                        fontSize = 16.sp,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable { navController.navigate("signup") }
                    )
                }
            }
        }
    }
}
