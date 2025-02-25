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
import androidx.navigation.compose.rememberNavController
import dcu.fossilfuel.data.api.ApiClient
import dcu.fossilfuel.data.api.ApiService
import dcu.fossilfuel.ui.GuestbookScreen
import dcu.fossilfuel.ui.HomeScreen
import dcu.fossilfuel.ui.NavigationGraph
import dcu.fossilfuel.ui.theme.FossilfuelTheme

class MainActivity : ComponentActivity() {
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val apiService = ApiClient.getClient().create(ApiService::class.java)

        setContent {
            FossilfuelTheme {
                val navController = rememberNavController() // NavController 생성

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        Column(modifier = Modifier.padding(innerPadding)) {
                            // 버튼을 통한 화면 전환
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Button(onClick = { navController.navigate("home") }) { // Home으로 네비게이션
                                    Text("메인")
                                }
                                Button(onClick = { navController.navigate("guestbook") }) { // Guestbook으로 네비게이션
                                    Text("방명록")
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // NavigationGraph를 통해 화면 전환 관리
                            NavigationGraph(navController = navController, apiService = apiService)
                        }
                    }
                )
            }
        }
    }
}
