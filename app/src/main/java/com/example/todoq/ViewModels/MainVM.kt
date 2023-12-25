package com.example.todoq.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoq.firestore.AppRepository
import com.example.todoq.firestore.TodoData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainVM: ViewModel() {
    private val repository = AppRepository()

    private var _todoList = MutableStateFlow<List<TodoData>>(emptyList())
    val todoList: StateFlow<List<TodoData>> = _todoList.asStateFlow()

    // getTodo function
    fun getTodo(db: FirebaseFirestore) {
        viewModelScope.launch {
            val newList = repository.getTodo(
                db = db,
            )
            _todoList.value = newList
        }
    }

    // deleteTodo function
    fun deleteTodo(db: FirebaseFirestore, collection: String, document: String, context: Context) {
        viewModelScope.launch {
            repository.deleteTodo(
                db = db,
                collection = collection,
                document = document,
                context = context,
            )
        }
    }
}