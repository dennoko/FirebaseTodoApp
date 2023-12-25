package com.example.todoq.ViewModels

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoq.firestore.AppRepository
import com.example.todoq.firestore.TodoData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddTodoVM: ViewModel() {
    // Todo: title
    var title = mutableStateOf("")
    var tagName = mutableStateOf("")
    var description = mutableStateOf("")

    // initialize Repository
    private val repository = AppRepository()
    // addTodo function
    fun addTodo(db: FirebaseFirestore, context: Context) {
        if(title.value != "") {
            repository.addTodo(
                title = title.value,
                tagName = tagName.value,
                description = description.value,
                db = db,
            )
        } else {
            // Toast
            Toast.makeText(
                context,
                "Please enter the title",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}