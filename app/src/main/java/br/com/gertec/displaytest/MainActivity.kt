package br.com.gertec.displaytest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.gertec.displaytest.screens.DisplayScreen
import br.com.gertec.displaytest.screens.MainScreen
import br.com.gertec.displaytest.ui.theme.DisplayTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navigation()
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main_screen")
    {
        composable("main_screen") {
            MainScreen(navController)
        }
        composable("display_screen") {
            DisplayScreen(navController)
        }

    }
}