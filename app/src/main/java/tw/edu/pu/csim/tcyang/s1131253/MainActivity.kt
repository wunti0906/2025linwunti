package tw.edu.pu.csim.tcyang.s1131253

import android.content.pm.ActivityInfo // 導入用於設定直式螢幕
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat // 導入 WindowCompat
import androidx.core.view.WindowInsetsCompat // 導入 WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat // 導入 WindowInsetsControllerCompat
import tw.edu.pu.csim.tcyang.s1131253.ui.theme.S1131253Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 【1. 強制螢幕為直式 (PORTRAIT)】
        // 程式碼要求在 MainActivity.kt 中設定
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // 【2. 隱藏上方狀態列及下方巡覽列 (全螢幕/沉浸模式)】
        // 現代 Android 全螢幕設定方法（推薦用於 Jetpack Compose）
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)

        // 設定隱藏狀態列和導覽列
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())

        // 設定行為：當使用者從邊緣滑動時，系統列會暫時出現
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        enableEdgeToEdge()
        setContent {
            S1131253Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    S1131253Theme {
        Greeting("Android")
    }
}