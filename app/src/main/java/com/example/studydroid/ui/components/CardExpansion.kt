package com.example.studydroid.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studydroid.R
import com.example.studydroid.ui.theme.StudyDroidTheme

@Composable
fun CardExpansion(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    description: String,
    expanded: Boolean = false,
    onIconClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 12.dp)
            ) {
                Text(text = title)
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                if (expanded) Text(text = description)
            }
            IconButton(onClick = onIconClick) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) {
                        stringResource(R.string.show_less_content_description)
                    } else {
                        stringResource(R.string.show_more_content_description)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardExpansionPreview() {
    StudyDroidTheme {
        CardExpansion(
            title = "title",
            subtitle = "subtitle",
            description = "description ".repeat(20),
            onIconClick = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardExpansionExpandedPreview() {
    StudyDroidTheme {
        CardExpansion(
            title = "title",
            subtitle = "subtitle",
            description = "description ".repeat(20),
            onIconClick = { },
            expanded = true
        )
    }
}