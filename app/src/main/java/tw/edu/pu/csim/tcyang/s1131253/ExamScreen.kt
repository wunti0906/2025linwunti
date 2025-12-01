package tw.edu.pu.csim.tcyang.s1131253

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExamScreen(
    viewModel: ExamViewModel,
    modifier: Modifier = Modifier       // 一定要有這行 + 預設值
) {
    val config = LocalConfiguration.current
    val width = config.screenWidthDp
    val height = config.screenHeightDp

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Yellow),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.happy),
                contentDescription = "快樂圖片",
                modifier = Modifier.size(280.dp)
            )

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "瑪利亞基金會服務大挑戰",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "作者：資管二B 林彣媞",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "螢幕大小 ${width}.0 × ${height}.0",
                fontSize = 16.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "成績：${viewModel.score.value} 分",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
        }
    }
}
