package com.example.rich_text

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposableScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Composable Preview") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "戻る"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Text(
                text = "タイトルテキスト",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                style = MaterialTheme.typography.titleMedium
            )
            RichContentComposable(
                modifier = Modifier.weight(1f)
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                onClick = { Toast.makeText(context, "ボタンがクリックされました", Toast.LENGTH_SHORT).show() }
            ) {
                Text(text = "トーストボタン")
            }
        }
    }
}

@Composable
fun RichContentComposable(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(15.dp)
    ) {
        // H1 見出し
        Text(
            text = "H1 見出し (Title)",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF6200EE),
            modifier = Modifier
                .drawBehind {
                    val strokeWidth = 2.dp.toPx()
                    val y = size.height - strokeWidth / 2
                    drawLine(Color(0xFF6200EE), Offset(0f, y), Offset(size.width, y), strokeWidth)
                }
                .padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // H2 見出し
        Text(
            text = "H2 見出し (Subtitle)",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF03DAC6),
            modifier = Modifier
                .drawBehind {
                    val strokeWidth = 5.dp.toPx()
                    drawLine(Color(0xFF03DAC6), Offset(strokeWidth / 2, 0f), Offset(strokeWidth / 2, size.height), strokeWidth)
                }
                .padding(start = 15.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 装飾テキスト
        Text(buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("太字 (Bold)") }
            append(", ")
            withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append("斜体 (Italic)") }
            append(", ")
            withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) { append("訂正線 (Strikethrough)") }
            append(", ")
            withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) { append("下線 (Underline)") }
        })

        Spacer(modifier = Modifier.height(12.dp))

        // カラーテキスト
        Text("テキストカラー (10色):", fontWeight = FontWeight.Bold)
        Row {
            val colors = listOf(Color.Red, Color.Blue, Color.Green, Color(0xFFFFA500), Color(0xFF800080))
            colors.forEach { Text("Text ", color = it) }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // チェックリスト
        Text("チェックリスト:", fontWeight = FontWeight.Bold)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = true, onCheckedChange = {})
            Text("完了タスク")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = false, onCheckedChange = {})
            Text("未完了タスク")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 引用
        Box(
            modifier = Modifier
                .drawBehind {
                    val strokeWidth = 4.dp.toPx()
                    drawLine(Color.LightGray, Offset(strokeWidth / 2, 0f), Offset(strokeWidth / 2, size.height), strokeWidth)
                }
                .padding(start = 15.dp)
        ) {
            Text(
                text = "「困難の中にこそ、チャンスがある。」 — アルベルト・アインシュタイン",
                style = MaterialTheme.typography.bodyMedium.copy(fontStyle = FontStyle.Italic),
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        HorizontalDivider(thickness = 1.dp, color = Color.Gray)

        Spacer(modifier = Modifier.height(12.dp))

        // 配置
        Text("← 左寄せ", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start)
        Text("-- 中央寄せ --", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Text("右寄せ →", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End)

        Spacer(modifier = Modifier.height(12.dp))

        // コードブロック
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF4F4F4))
                .padding(10.dp)
        ) {
            Text(
                text = "fun main() {\n    println(\"Hello Compose!\")\n}",
                fontFamily = FontFamily.Monospace,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 簡易テーブル再現
        Text("表 (Table):", fontWeight = FontWeight.Bold)
        Column(modifier = Modifier.background(Color.White)) {
            Row(modifier = Modifier.background(Color(0xFFF2F2F2))) {
                Text(" 項目 ", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                Text(" 価格 ", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            }
            HorizontalDivider()
            Row {
                Text(" Android Phone ", modifier = Modifier.weight(1f))
                Text(" ¥50,000 ", modifier = Modifier.weight(1f))
            }
        }
    }
}
