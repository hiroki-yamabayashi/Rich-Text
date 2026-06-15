package com.example.rich_text

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rich_text.ui.theme.RichTextTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RichTextTheme {
                AppNavigation()
            }
        }
    }
}

@Serializable
object InitialRoute

@Serializable
object WebViewRoute

@Serializable
object ComposableRoute

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = InitialRoute
    ) {
        composable<InitialRoute> {
            InitialScreen(
                onNavigateToWebView = {
                    navController.navigate(WebViewRoute)
                },
                onNavigateToComposable = {
                    navController.navigate(ComposableRoute)
                }
            )
        }
        composable<WebViewRoute> {
            WebViewScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable<ComposableRoute> {
            ComposableScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
