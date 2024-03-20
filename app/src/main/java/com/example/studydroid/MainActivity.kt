package com.example.studydroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studydroid.ui.components.CardExpansion
import com.example.studydroid.ui.theme.StudyDroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var expanded by rememberSaveable { mutableStateOf(false) }
            StudyDroidTheme {
                CardExpansion(
                    modifier = Modifier.padding(12.dp),
                    title = "Hello",
                    subtitle = "World",
                    description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.".repeat(2),
                    onIconClick = { expanded = !expanded },
                    expanded = expanded
                )
            }
        }
    }
}