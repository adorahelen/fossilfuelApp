package dcu.fossilfuel.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dcu.fossilfuel.R

@Composable
fun MemoriesSection() {
    Surface(
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Our Memories", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.Center) {
                listOf(
                    R.drawable.assemble4,
                    R.drawable.assemble5,
                    R.drawable.assemble1,
                    R.drawable.assemble2,
                    R.drawable.assemble3
                ).forEach { memory ->
                    Image(
                        painter = painterResource(id = memory),
                        contentDescription = "Memory",
                        modifier = Modifier
                            .width(200.dp)
                            .height(150.dp)
                            .padding(horizontal = 10.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}