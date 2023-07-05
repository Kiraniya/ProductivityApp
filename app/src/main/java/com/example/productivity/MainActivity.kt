package com.example.productivity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.productivity.ui.theme.ProductivityTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: TimerViewModel by viewModels()
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todoViewModel:Todoviewmodel by viewModels()
        setContent {
            ProductivityTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //val timerRunning=true
                    //val cycleComp=2
                    //Timer(viewModel = viewModel)
                    //SecondScreen(vm = todoViewModel)
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = { BottomNavigation(navController = navController) }
                    ) {

                        NavHost(navController, startDestination =
                        navitem.TimerS.screen_route) {
                            composable(navitem.TimerS.screen_route) {
                                Timer(viewModel = viewModel)
                            }
                            composable(navitem.TodoS.screen_route) {
                                SecondScreen(vm = todoViewModel)
                            }
                        }
                    }
                }
                }
            }
        }
    }



