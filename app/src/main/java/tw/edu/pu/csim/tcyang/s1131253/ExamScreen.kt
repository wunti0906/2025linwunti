// ExamScreen.kt  （這個檔案必須單獨存在！）
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExamScreen(
    viewModel: ExamViewModel,
    modifier: Modifier = Modifier
) {
    val widthDp = LocalConfiguration.current.screenWidthDp
    val heightDp = LocalConfiguration.current.screenHeightDp

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        // 左上 role0
        Image(
            painter = painterResource(R.drawable.role0),
            contentDescription = "嬰幼兒",
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.TopStart)
                .offset(16.dp, 40.dp)
        )

        // 右上 role1
        Image(
            painter = painterResource(R.drawable.role1),
            contentDescription = "兒童",
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.TopEnd)
                .offset((-16).dp, 40.dp)
        )

        // 左下 role2
        Image(
            painter = painterResource(R.drawable.role2),
            contentDescription = "成人",
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.BottomStart)
                .offset(16.dp, (10).dp)
        )

        // 右下 role3
        Image(
            painter = painterResource(R.drawable.role3),
            contentDescription = "一般民眾",
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.BottomEnd)
                .offset((-16).dp, (10).dp)
        )

        // 中間內容
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.happy),
                contentDescription = "服務快樂",
                modifier = Modifier.size(180.dp)  // 已縮小
            )

            Spacer(Modifier.height(16.dp))
            Text("瑪利亞基金會服務大挑戰",
                fontSize = 16.sp,color = Color.Black)

            Spacer(Modifier.height(16.dp))
            Text("作者：林彣媞",
                fontSize = 16.sp, color = Color.DarkGray)

            Spacer(Modifier.height(16.dp))
            Text("螢幕大小 ${widthDp}.0 × ${heightDp}.0",
                fontSize = 16.sp, color = Color.Black)

            Spacer(Modifier.height(16.dp))
            Text("成績：${viewModel.score.value} 分",
                fontSize = 16.sp, color = Color.Black)
        }
    }
}
