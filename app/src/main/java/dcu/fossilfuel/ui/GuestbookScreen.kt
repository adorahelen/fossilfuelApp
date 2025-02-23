package dcu.fossilfuel.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dcu.fossilfuel.data.api.ApiService
import dcu.fossilfuel.data.model.Guestbook

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



@Composable
fun GuestbookScreen(apiService: ApiService) {
    var guestbookEntries by remember { mutableStateOf(listOf<Guestbook>()) }
    var newEntryText by remember { mutableStateOf(TextFieldValue("")) }
    var currentPage by remember { mutableStateOf(1) }
    val entriesPerPage = 6

    LaunchedEffect(Unit) {
        fetchGuestbookEntries(apiService) { entries ->
            guestbookEntries = entries
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("방명록", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        // 방명록 입력
        OutlinedTextField(
            value = newEntryText,
            onValueChange = { newEntryText = it },
            label = { Text("새 방명록 작성") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (newEntryText.text.isNotBlank()) {
                    addGuestbookEntry(apiService, newEntryText.text) {
                        newEntryText = TextFieldValue("")
                        fetchGuestbookEntries(apiService) { entries ->
                            guestbookEntries = entries
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("작성")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 방명록 목록
        val start = (currentPage - 1) * entriesPerPage
        val end = (start + entriesPerPage).coerceAtMost(guestbookEntries.size)
        val pageEntries = guestbookEntries.subList(start, end)

        Box(modifier = Modifier.weight(1f)) {
            LazyColumn {
                items(pageEntries) { entry ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Text(
                            text = entry.content,
                            modifier = Modifier.padding(16.dp),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }

        // 페이지네이션
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { if (currentPage > 1) currentPage-- },
                enabled = currentPage > 1
            ) { Text("이전") }

            Spacer(modifier = Modifier.width(16.dp))

            Text("페이지 $currentPage", fontSize = 16.sp)

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { if (currentPage * entriesPerPage < guestbookEntries.size) currentPage++ },
                enabled = currentPage * entriesPerPage < guestbookEntries.size
            ) { Text("다음") }
        }
    }
}

// 방명록 가져오기
private fun fetchGuestbookEntries(apiService: ApiService, onResult: (List<Guestbook>) -> Unit) {
    apiService.getGuestbookEntries().enqueue(object : Callback<List<Guestbook>> {
        override fun onResponse(call: Call<List<Guestbook>>, response: Response<List<Guestbook>>) {
            if (response.isSuccessful) {
                onResult(response.body() ?: emptyList())
            } else {
                onResult(emptyList())
            }
        }

        override fun onFailure(call: Call<List<Guestbook>>, t: Throwable) {
            onResult(emptyList())
        }
    })
}

// 방명록 추가
private fun addGuestbookEntry(apiService: ApiService, content: String, onResult: () -> Unit) {
    val newEntry = Guestbook(content = content)
    apiService.addGuestbookEntry(newEntry).enqueue(object : Callback<Guestbook> {
        override fun onResponse(call: Call<Guestbook>, response: Response<Guestbook>) {
            if (response.isSuccessful) {
                onResult()
            }
        }

        override fun onFailure(call: Call<Guestbook>, t: Throwable) {}
    })
}