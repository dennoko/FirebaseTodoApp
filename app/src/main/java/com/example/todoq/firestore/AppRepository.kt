package com.example.todoq.firestore

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AppRepository {
    // addTodo function
    fun addTodo(title: String, tagName: String, description: String, db: FirebaseFirestore) {
        val todo = hashMapOf(
            "title" to title,
            "tagName" to tagName,
            "description" to description,
        )

        db.collection("dennoko").document(title) // collection name
            .set(todo)
            .addOnSuccessListener { documentReference ->
                Log.d("methodTest","[AppRepository] DocumentSnapshot added with ID: ${title}")
            }
            .addOnFailureListener {e ->
                Log.w("methodTest","[AppRepository] Error adding document${e.message} ${e.cause}")
            }
    }

    // getTodo function
    suspend fun getTodo(db: FirebaseFirestore): List<TodoData> {
        val todoList = mutableListOf<TodoData>()

        try {
            val result = db.collection("dennoko").get().await()
            for (document in result) {
                Log.d("methodTest", "[AppRepository] ${document.id} => ${document.data}")
                // add TodoData to list
                todoList.add(TodoData(
                    title = document.data["title"] as String,
                    tagName = document.data["tagName"] as String,
                    description = document.data["description"] as String,
                ))
            }
        } catch (e: Exception) {
            Log.d("methodTest", "[AppRepository] Error getting documents: ${e.message}  ${e.cause}")
        }

        return todoList
    }

    // deleteTodo function
    suspend fun deleteTodo(db: FirebaseFirestore, collection: String, document: String, context: Context) {
        val todoRef = db.collection(collection).document(document)
        todoRef.delete()
            .addOnSuccessListener {
                Toast.makeText(
                    context,
                    "Successfully deleted",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    context,
                    "Error deleting document",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("methodTest", "[AppRepository] Error deleting document: ${e.message} ${e.cause}")
            }
    }
}