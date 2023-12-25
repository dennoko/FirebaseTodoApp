package com.example.todoq.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoq.ui.theme.AppTheme

@Composable
fun TitleText(title: String) {
    Text(
        text = title,
        fontSize = 40.sp,
        fontWeight = FontWeight.ExtraBold,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
            .border(3.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
            .shadow(10.dp, RoundedCornerShape(16.dp))
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(20.dp, 10.dp)
        )
}

@Preview
@Composable
fun TitleTextPreview() {
    AppTheme {
        TitleText("TodoQ")
    }
}