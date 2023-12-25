package com.example.todoq

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoq.ViewModels.AddTodoVM
import com.example.todoq.ViewModels.MainVM
import com.example.todoq.screen.AddTodoScreen
import com.example.todoq.screen.MainScreen
import com.example.todoq.ui.theme.AppTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // initialize Firebase
        val db = Firebase.firestore

        setContent {
            // navController
            val navController = rememberNavController()

            AppTheme{
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "main" ) {
                        composable("main") {
                            val vm: MainVM = viewModel()
                            MainScreen(db, navController, vm)
                        }
                        composable("addTodo") {
                            val vm: AddTodoVM = viewModel()
                            AddTodoScreen(db, navController, vm)
                        }
                    }
                }
            }
        }
    }
}