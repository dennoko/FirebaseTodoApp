package com.example.todoq.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import com.example.todoq.ui_components.TitleText
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoq.ViewModels.AddTodoVM

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddTodoScreen(
    db: FirebaseFirestore,
    navController: NavController,
    vm: AddTodoVM,
) {
    // control keyboard
    val keyboardController = LocalSoftwareKeyboardController.current
    // focus management
    val focusManager = LocalFocusManager.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
            ) {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        TitleText(title = "Add Todo")
        Spacer(modifier = Modifier.height(16.dp))

        // Todo Form
        TodoFormTitle(txt = "Title")
        BasicTextField(
            value = vm.title.value,
            onValueChange = { vm.title.value = it },
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant),
            modifier = Modifier
                .fillMaxWidth()
                .padding(64.dp, 16.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        )

        TodoFormTitle(txt = "Tag Name")
        BasicTextField(
            value = vm.tagName.value,
            onValueChange = { vm.tagName.value = it },
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant),
            modifier = Modifier
                .fillMaxWidth()
                .padding(64.dp, 16.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        )

        TodoFormTitle(txt = "Description")
        BasicTextField(
            value = vm.description.value,
            onValueChange = { vm.description.value = it },
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant),
            modifier = Modifier
                .fillMaxWidth()
                .padding(64.dp, 16.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        // Todo Add Button
        Button(
            onClick = {
                vm.addTodo(db = db, context = navController.context)
                vm.title.value = ""
                vm.tagName.value = ""
                vm.description.value = ""
                keyboardController?.hide()
                focusManager.clearFocus()
                      },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .shadow(10.dp, RoundedCornerShape(8.dp))
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Text(
                text = "Add Todo",
                fontSize = 20.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimary,
            )

        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Button(
                onClick = { navController.navigate("main") },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(64.dp, 4.dp)
                    .shadow(10.dp, RoundedCornerShape(8.dp))
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Back",
                    fontSize = 20.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun TodoFormTitle(txt: String) {
    Text(
        text = txt,
        fontSize = 20.sp,
        fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onBackground,
        )
}