package com.example.studydroid.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studydroid.ui.theme.StudyDroidTheme

@Composable
fun TextFieldGradient(
    modifier: Modifier = Modifier,
    textFieldValue: String,
    onTextChanged: (String) -> Unit
) {
        TextField(
            value = textFieldValue,
            onValueChange = {onTextChanged(it)},
            modifier = modifier
                .border(
                    border = BorderStroke(
                        brush = Brush.linearGradient(listOf(Color.Red, Color.Blue)),
                        width = 2.dp
                    ),
                    shape = CutCornerShape(12.dp)
                )
        )
}

@Preview
@Composable
private fun TextFieldGradientPreview() {
    StudyDroidTheme {
        TextFieldGradient(
            textFieldValue = "Hello Again",
            onTextChanged = {}
        )
    }
}