package dcu.fossilfuel.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun StudySection() {
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
            Text(
                text = "컴퓨터소프트웨어학부",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
            Text(
                text = "학과 동아리",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "설립 목적",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))
            Column {
                Text("• 성장과 동기 부여를 위한 환경 조성", style = MaterialTheme.typography.bodyLarge)
                Text("• 사회에 나갈 방향성 함께 고민 & 준비", style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "활동 내용",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))
            Column {
                listOf(
                    "• 프로젝트 기획 및 피드백 지원",
                    "• 채용 공고 공유 및 지원 전략 검토",
                    "• 자기 소개서 및 이력서 피드백 제공",
                    "• 주 5일 자유롭게 참여(09:00~22:00)",
                    "• 학습 자료 공유, 효율적 공부 환경 제공",
                    "• 선후배 간 자유로운 멘토링 및 네트워킹",
                    "• 자격증 준비 (정보처리기사, 정보보안기사)",
                    "• 코딩 테스트 연습 (하루 1문제 또는 주 2문제)",
                ).forEach {
                    Text(it, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}