package com.example.todoq.ui_components

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoq.ViewModels.MainVM
import com.example.todoq.firestore.TodoData
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun DisplayTodo(todoList: List<TodoData>, context: Context, vm: MainVM = viewModel()) {
    Column {
        todoList.forEach { todo ->
            TodoItem(
                todo = todo,
                deleteTodo = {
                    vm.deleteTodo(
                        db = Firebase.firestore,
                        collection = "dennoko",
                        document = todo.title,
                        context = context,
                    )

                    // Reload the todoList
                    if(vm.searchTag.value != "") {
                        vm.getTodoByTag(
                            db = Firebase.firestore,
                            tagName = vm.searchTag.value,
                        )
                    } else {
                        vm.getTodo(db = Firebase.firestore)
                    }
                }
            )
        }
    }
}