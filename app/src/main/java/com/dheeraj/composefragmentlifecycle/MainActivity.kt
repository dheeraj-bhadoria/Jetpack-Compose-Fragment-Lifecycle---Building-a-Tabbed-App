package com.dheeraj.composefragmentlifecycle

import Screen1
import Screen2
import Screen3
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dheeraj.composefragmentlifecycle.ui.theme.ComposeFragmentLifecycleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFragmentLifecycleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    val screens = listOf("profile", "feed", "screen3")
    var currentScreen by remember { mutableStateOf(screens.first()) }

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycle1 = lifecycleOwner.lifecycle
    val lifecycle2 = lifecycleOwner.lifecycle
    val lifecycle3 = lifecycleOwner.lifecycle

    Scaffold(
        topBar = {
            TabRow(selectedTabIndex = screens.indexOf(currentScreen)) {
                screens.forEachIndexed { index, screen ->
                    Tab(
                        selected = currentScreen == screen,
                        onClick = {
                            currentScreen = screen
                            navController.navigate(screen)
                        },
                        text = {
                            when (index) {
                                0 -> Text("My Profile")
                                1 -> Text("Feed")
                                2 -> Text("Setting")
                                else -> Text("Unknown")
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = screens.first(),
            Modifier.padding(innerPadding)
        ) {
            composable(route = "profile") {
                Screen1(lifecycle1)
            }
            composable(route = "feed") {
                Screen2(lifecycle2)
            }
            composable(route = "screen3") {
                Screen3(lifecycle3)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeFragmentLifecycleTheme {
        App()
    }
}






