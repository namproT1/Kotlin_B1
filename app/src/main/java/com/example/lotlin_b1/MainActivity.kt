package com.example.lotlin_b1

import android.os.Bundle
import android.service.credentials.CreateEntry
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.lotlin_b1.ui.theme.Lotlin_B1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lotlin_B1Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController) }
                    composable("home/{username}") { backStackEntry ->
                        val username = backStackEntry.arguments?.getString("username") ?: "User"
                        HomeScreen(username, navController)
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.llk),
            contentDescription = "Login",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Red, CircleShape)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Login", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.Red)

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("User Name") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(10.dp))
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(10.dp))
        Row (

        ) {
            Button(
                onClick = {
                    if (username.isNotEmpty() && password.isNotEmpty()) {
                        if (username == "admin" && password == "12345") {
                            navController.navigate("home/$username")
                        } else {
                            errorMessage = "Sai thông tin đăng nhập!"
                        }
                    } else {
                        errorMessage = "Vui lòng nhập đầy đủ thông tin!"
                    }
                },
                modifier = Modifier
                    .padding(end = 10.dp)
                    .weight(1f),
                shape = CutCornerShape(10.dp)
            ) {
                Text("Login")
            }
            Button(onClick = {""},
                modifier = Modifier
                    .padding(end = 10.dp)
                    .weight(1f),
                shape = CutCornerShape(10.dp)
            )
            { Text(text = "Cancel")}
        }
    }
}

@Composable
fun HomeScreen(username: String, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome, $username!", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack("login", inclusive = false) }) {
            Text("Logout")
        }
    }
}
