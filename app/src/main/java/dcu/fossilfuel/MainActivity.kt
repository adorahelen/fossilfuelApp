package dcu.fossilfuel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dcu.fossilfuel.data.api.ApiClient
import dcu.fossilfuel.data.api.ApiService
import dcu.fossilfuel.ui.GuestbookScreen
import dcu.fossilfuel.ui.HomeScreen
import dcu.fossilfuel.ui.SignupScreen
import dcu.fossilfuel.ui.theme.FossilfuelTheme


class MainActivity : ComponentActivity() {
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        apiService = ApiClient.getClient().create(ApiService::class.java)

        setContent {
            FossilfuelTheme {
                var currentScreen by remember { mutableStateOf("Home") }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        Column(modifier = Modifier.padding(innerPadding)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Button(onClick = { currentScreen = "Home" }) {
                                    Text("메인")
                                }
                                Button(onClick = { currentScreen = "Guestbook" }) {
                                    Text("방명록")
                                }
                                Button(onClick = { currentScreen = "Signup" }) {
                                    Text("회원가입")
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            when (currentScreen) {
                                "Home" -> HomeScreen(apiService)
                                "Guestbook" -> GuestbookScreen(apiService)
                                "Signup" -> SignupScreen(apiService)
                            }
                        }
                    }
                )
            }
        }
    }
}