package dcu.fossilfuel.ui


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dcu.fossilfuel.data.api.ApiService

@Composable
fun NavigationGraph(navController: NavHostController, apiService: ApiService) {
    NavHost(navController = navController, startDestination = "home") {

        composable("home") { HomeScreen(navController, apiService) }
        composable("guestbook") { GuestbookScreen(apiService) }
        composable("signup") { SignupScreen(navController,apiService) }
        composable("chatbot") { ChatBotScreen(navController) }
        composable("login") { LoginScreen(navController) }




    }
}
