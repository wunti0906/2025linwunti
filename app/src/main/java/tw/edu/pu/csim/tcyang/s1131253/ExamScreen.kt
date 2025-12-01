// ExamScreen.kt  ← 獨立檔案
package tw.edu.pu.csim.tcyang.s1131253

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset  // ← 新增：修復 offset {} 內的 Offset 紅字
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun ExamScreen(
    viewModel: ExamViewModel,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp
    val screenWidthPx = with(density) { screenWidthDp.toPx() }
    val screenHeightPx = with(density) { screenHeightDp.toPx() }

    val iconSize = 100.dp
    val iconSizePx = with(density) { iconSize.toPx() }

    // 掉落動畫相關狀態
    var currentIconIndex by remember { mutableStateOf(0) }
    val offsetY = remember { Animatable(-200f) }           // 垂直位置（px）
    var offsetX by remember { mutableStateOf(0.dp) }       // 水平偏移（dp）

    val serviceIcons = listOf(
        R.drawable.service0,
        R.drawable.service1,
        R.drawable.service2,
        R.drawable.service3
    )

    // 無限掉落 + 每掉完一顆就重新開始
    LaunchedEffect(Unit) {
        while (true) {
            // 隨機選一張圖
            currentIconIndex = (0..3).random()

            // 重置位置
            offsetY.snapTo(-200f)
            offsetX = 0.dp

            // 掉落動畫（約 4.5 秒從頂端掉到螢幕外）
            offsetY.animateTo(
                targetValue = screenHeightPx + 200f,
                animationSpec = tween(durationMillis = 4500)
            )

            // 掉到螢幕外後短暫延遲再出下一顆（避免太密集）
            delay(300)
        }
    }

    Box(
        modifier = modifier  // ← 修正：移除多餘的 "modifier" 關鍵字，現在是正確的 .fillMaxSize() 鏈式
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        // 四個角落角色
        Image(
            painter = painterResource(R.drawable.role0),
            contentDescription = "嬰幼兒",
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = (screenHeightDp / 2 - 65.dp))
        )
        Image(
            painter = painterResource(R.drawable.role1),
            contentDescription = "兒童",
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.TopEnd)
                .padding(end = 16.dp, top = (screenHeightDp / 2 - 65.dp))
        )
        Image(
            painter = painterResource(R.drawable.role2),
            contentDescription = "成人",
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 16.dp)
        )
        Image(
            painter = painterResource(R.drawable.role3),
            contentDescription = "一般民眾",
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp)
        )

        // 中間文字資訊
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.happy),
                contentDescription = null,
                modifier = Modifier.size(180.dp)
            )
            Spacer(Modifier.height(16.dp))
            Text("瑪利亞基金會服務大挑戰", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(Modifier.height(8.dp))
            Text("作者：林彣媞", fontSize = 16.sp, color = Color.Black)
            Spacer(Modifier.height(16.dp))
            Text(
                "螢幕大小 ${configuration.screenWidthDp} × ${configuration.screenHeightDp}",
                fontSize = 20.sp,
                color = Color.Black
            )
            Spacer(Modifier.height(16.dp))
            Text(
                "成績：${viewModel.score.value} 分",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // 掉落的服務圖示（可左右拖曳 + 邊界限制）
        Image(
            painter = painterResource(serviceIcons[currentIconIndex]),
            contentDescription = "服務圖示",
            modifier = Modifier
                .size(iconSize)
                .offset {
                    val centerX = screenWidthPx / 2f - iconSizePx / 2f
                    val rawX = centerX + with(density) { offsetX.toPx() }
                    val clampedX = rawX.coerceIn(0f, screenWidthPx - iconSizePx)

                    IntOffset(
                        x = clampedX.roundToInt(),
                        y = offsetY.value.roundToInt()
                    )
                }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        // 轉成 dp 再累加，避免精度問題
                        offsetX += with(density) { dragAmount.x.toDp() }

                        // 即時限制邊界（dp 版）
                        val maxOffset = (screenWidthDp - iconSize) / 2
                        offsetX = offsetX.coerceIn(-maxOffset, maxOffset)
                    }
                }
        )
    }
}