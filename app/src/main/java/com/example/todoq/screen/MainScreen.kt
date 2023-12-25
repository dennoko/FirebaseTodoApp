package com.example.todoq.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todoq.ViewModels.AddTodoVM
import com.example.todoq.ViewModels.MainVM
import com.example.todoq.ui_components.DisplayTodo
import com.example.todoq.ui_components.TitleText
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(db: FirebaseFirestore, navController: NavController, vm: MainVM) {
    val keyboard = LocalSoftwareKeyboardController.current
    val focus = LocalFocusManager.current

    Log.d("methodTest", "[MainScreen] called or reComposed")
    LaunchedEffect(Unit) {
        vm.getTodo(db = db)
    }

    val todoList = vm.todoList.collectAsState()

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("addTodo") },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ) {
                // add icon
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        },
        content = {innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.background)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) {
                        focus.clearFocus()
                        keyboard?.hide()
                    }
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                TitleText("Todo App")
                Spacer(modifier = Modifier.height(64.dp))

                // display todoList
                Text(
                    text = "Todo List",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )

                Divider()

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Tag",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                    BasicTextField(
                        value = vm.searchTag.value,
                        onValueChange = {
                            vm.searchTag.value = it
                        },
                        maxLines = 1,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                1.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .weight(1f)
                            .padding(8.dp)
                    )
                    Button(
                        onClick = {
                            vm.getTodoByTag(db = db, tagName = vm.searchTag.value)
                            keyboard?.hide()
                            focus.clearFocus()
                        },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(4.dp)
                            .shadow(10.dp, RoundedCornerShape(8.dp))
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        Text(
                            text = "Search",
                            fontSize = 20.sp,
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))
                }
                Divider()

                DisplayTodo(todoList.value, context = navController.context)
            }
        }
    )
}