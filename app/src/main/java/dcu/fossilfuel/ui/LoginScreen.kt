package dcu.fossilfuel.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = androidx.compose.ui.graphics.Brush.linearGradient(
                colors = listOf(Color(0xFF0057B7), Color(0xFFFFD700))
            ))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 로그인 박스
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(20.dp)
                .border(1.dp, Color.Gray)
                .padding(16.dp)
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "로그인",
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 24.sp),
                modifier = Modifier.padding(8.dp)
            )

            // 이메일 입력 필드
            BasicTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .border(2.dp, Color(0xFF0057B7))
                    .padding(10.dp)
            )

            // 비밀번호 입력 필드
            BasicTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .border(2.dp, Color(0xFF0057B7))
                    .padding(10.dp)
            )

            // 로그인 버튼
            Button(
                onClick = {
                    if (email.text.isNotBlank() && password.text.isNotBlank()) {
                        if (onLogin(email.text, password.text)) {
                            // 로그인 성공 후 대쉬보드로 이동
                            navController.navigate("dashboard")
                        } else {
                            errorMessage = "로그인 실패: 잘못된 자격 증명입니다."
                        }
                    } else {
                        errorMessage = "이메일과 비밀번호를 입력하세요."
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(Color(0xFF0057B7))
            ) {
                Text("로그인", color = Color.White)
            }

            // 에러 메시지 표시
            if (errorMessage.isNotBlank()) {
                Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(8.dp))
            }

            // 아이디 찾기 & 비밀번호 찾기 버튼
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = {
                    navController.navigate("find_id") // 아이디 찾기 화면으로 이동
                }, modifier = Modifier.weight(1f).padding(4.dp)) {
                    Text("아이디 찾기", color = Color.Black)
                }
                Button(onClick = {
                    navController.navigate("find_password") // 비밀번호 찾기 화면으로 이동
                }, modifier = Modifier.weight(1f).padding(4.dp)) {
                    Text("비밀번호 찾기", color = Color.Black)
                }
            }
        }
    }
}



@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "대쉬보드 화면", style = MaterialTheme.typography.headlineSmall.copy(fontSize = 24.sp))
    }
}


fun onLogin(text: String, text1: String): Boolean {

    return TODO("Provide the return value")
}
