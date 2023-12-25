package com.example.todoq.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoq.firestore.TodoData
import com.example.todoq.ui.theme.AppTheme

@Composable
fun TodoItem(todo: TodoData, deleteTodo: () -> Unit) {
    var clicked = remember { mutableStateOf(false) }
    var checked = remember { mutableStateOf(false) }

    OutlinedButton(
        onClick = { clicked.value = !clicked.value },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp, 8.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp))
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            )
        ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                textAlign = TextAlign.Start,
                text = todo.title,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))

            if (clicked.value) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        textAlign = TextAlign.Start,
                        text = todo.description,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    Checkbox(
                        checked = checked.value,
                        onCheckedChange = {
                            deleteTodo()

                            checked.value = !checked.value
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TodoItemPreview() {
    val context = LocalContext.current

    AppTheme {
        TodoItem(todo = TodoData(
            title = "title",
            tagName = "tagName",
            description = "description",
        ), {})
    }
}