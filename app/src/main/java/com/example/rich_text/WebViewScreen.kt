package com.example.rich_text

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebViewScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Rich HTML Preview") },
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
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            CustomWebView(
                html = testHtml,
                modifier = Modifier.weight(1f),
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
private fun CustomWebView(
    html: String,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                setBackgroundColor(0)
                webViewClient = WebViewClient()
                @SuppressLint("SetJavaScriptEnabled")
                settings.javaScriptEnabled = true
                loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)
            }
        },
        update = { webView ->
            webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)
        }
    )
}

private val testHtml = """
        <!DOCTYPE html>
        <html>
        <head>
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <style>
                body { font-family: sans-serif; padding: 15px; line-height: 1.4; color: #333; }
                h1 { color: #6200EE; border-bottom: 2px solid #6200EE; }
                h2 { color: #03DAC6; border-left: 5px solid #03DAC6; padding-left: 10px; }
                h3 { color: #018786; text-decoration: underline; }
                blockquote { border-left: 4px solid #ccc; padding-left: 10px; color: #666; font-style: italic; }
                pre { background-color: #f4f4f4; padding: 10px; border-radius: 4px; overflow-x: auto; }
                table { border-collapse: collapse; width: 100%; margin: 10px 0; }
                th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
                th { background-color: #f2f2f2; }
                .color-box { display: inline-block; width: 20px; height: 20px; vertical-align: middle; margin-right: 5px; border-radius: 3px; }
                .checklist { list-style: none; padding: 0; }
                .checklist input { margin-right: 10px; }
            </style>
        </head>
        <body>
            <h1>H1 見出し (Title)</h1>
            <h2>H2 見出し (Subtitle)</h2>
            <h3>H3 見出し (Section)</h3>

            <p>
                <b>太字 (Bold)</b>, <i>斜体 (Italic)</i>, 
                <s>訂正線 (Strikethrough)</s>, <u>下線 (Underline)</u>
            </p>

            <p><strong>テキストカラー (10色):</strong><br>
                <span style="color:red">Red</span>, <span style="color:blue">Blue</span>, <span style="color:green">Green</span>,
                <span style="color:orange">Orange</span>, <span style="color:purple">Purple</span>, <span style="color:pink">Pink</span>,
                <span style="color:brown">Brown</span>, <span style="color:gray">Gray</span>, <span style="color:cyan">Cyan</span>,
                <span style="color:magenta">Magenta</span>
            </p>

            <p><strong>箇条書きリスト:</strong></p>
            <ul>
                <li>アイテム A</li>
                <li>アイテム B</li>
            </ul>

            <p><strong>番号付きリスト:</strong></p>
            <ol>
                <li>ステップ 1</li>
                <li>ステップ 2</li>
            </ol>

            <p><strong>リンク:</strong> <a href="https://developer.android.com">Android Developers</a></p>

            <p><strong>絵文字:</strong> 🚀 🌟 📱 🛠️ ✅</p>

            <p><strong>引用:</strong></p>
            <blockquote>
                「困難の中にこそ、チャンスがある。」 — アルベルト・アインシュタイン
            </blockquote>

            <hr>

            <p style="text-align: left;">← 左寄せ</p>
            <p style="text-align: center;">-- 中央寄せ --</p>
            <p style="text-align: right;">右寄せ →</p>

            <p><strong>背景色（ハイライト）:</strong> <mark>標準マーカー</mark>, <span style="background-color: #90EE90;">黄緑色ハイライト</span></p>

            <p><strong>画像挿入 (外部URL):</strong><br>
                <img src="https://raw.githubusercontent.com/bumptech/glide/master/static/glide_logo.png" alt="Sample Image" style="max-width: 100%; height: auto;">
            </p>

            <p><strong>チェックリスト:</strong></p>
            <ul class="checklist">
                <li><input type="checkbox" checked> 完了タスク</li>
                <li><input type="checkbox"> 未完了タスク</li>
            </ul>

            <p><strong>コード:</strong></p>
            <pre><code>fun main() {
    println("Hello WebView!")
}</code></pre>

            <p><strong>表 (Table):</strong></p>
            <table>
                <tr><th>項目</th><th>価格</th><th>在庫</th></tr>
                <tr><td>Android Phone</td><td>¥50,000</td><td>あり</td></tr>
                <tr><td>Tablet</td><td>¥30,000</td><td>なし</td></tr>
            </table>

            <p><strong>ファイル挿入 (リンク形式):</strong><br>
                <a href="sample.pdf">PDFファイルを開く (※動作制限あり)</a>
            </p>
        </body>
        </html>
    """.trimIndent()
