package dcu.fossilfuel.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun ChatBotScreen(navController: NavHostController) {
    var message by remember { mutableStateOf(TextFieldValue("")) }
    var chatMessages by remember { mutableStateOf(mutableListOf("AI 챗봇 - 똑똑한 고양이\n(고양이에게 말을 걸어보자)")) }
    var isTyping by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
            .border(1.dp, Color.Gray)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "AI 챗봇 - 똑똑한 고양이",
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp),
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Chat Box
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp)
                .border(1.dp, Color.Gray),
            verticalArrangement = Arrangement.Bottom
        ) {
            chatMessages.forEach { msg ->
                ChatMessage(msg, if (msg.startsWith("⚠️")) "bot-message" else "user-message")
            }
            if (isTyping) {
                TypingIndicator()
            }
        }

        // Input Field
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = message,
                onValueChange = { message = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                singleLine = true
            )
            Button(onClick = {
                if (message.text.isNotEmpty()) {
                    addUserMessage(message.text, chatMessages)
                    message = TextFieldValue("") // Clear input
                    simulateBotResponse(chatMessages, coroutineScope)
                }
            }) {
                Text("전송")
            }
        }
    }
}

@Composable
fun ChatMessage(text: String, className: String) {
    val transition = updateTransition(targetState = true, label = "Fade In Transition")
    val alpha by transition.animateFloat(label = "Opacity") { if (it) 1f else 0f }
    val offsetY by transition.animateDp(label = "Y Offset") { if (it) 0.dp else 5.dp }

    Box(
        modifier = Modifier
            .padding(4.dp)
            .alpha(alpha)
            .offset(y = offsetY)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .background(
                    if (className == "user-message") Color(0xFF4facfe).copy(0.9f) else Color(0xFFFCEABB).copy(0.9f),
                    shape = MaterialTheme.shapes.small
                )
                .padding(8.dp)
                .border(1.dp, Color.Gray, shape = MaterialTheme.shapes.small),
            color = if (className == "user-message") Color.White else Color.Black
        )
    }
}

@Composable
fun TypingIndicator() {
    Row(modifier = Modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically) {
        repeat(3) {
            val transition = rememberInfiniteTransition()
            val animatedValue by transition.animateFloat(
                initialValue = 0.3f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 1200, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .padding(2.dp)
                    .background(Color.Gray.copy(alpha = animatedValue), shape = MaterialTheme.shapes.small)
            )
        }
    }
}

private fun addUserMessage(text: String, chatMessages: MutableList<String>) {
    chatMessages.add(text)
}

private fun simulateBotResponse(chatMessages: MutableList<String>, coroutineScope: CoroutineScope) {
    chatMessages.add("⏳ AI가 응답하는 중...")
    coroutineScope.launch {
        delay(2000) // Simulate network delay
        chatMessages.removeAt(chatMessages.size - 1) // Remove typing indicator
        chatMessages.add("🤖 AI의 응답 메시지입니다.") // Simulated AI response
    }
}
