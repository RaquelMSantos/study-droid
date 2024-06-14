package com.example.studydroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studydroid.ui.components.BottomNavigationBar
import com.example.studydroid.ui.components.CardExpansion
import com.example.studydroid.ui.components.LeftNavigationRail
import com.example.studydroid.ui.components.SearchBar
import com.example.studydroid.ui.theme.StudyDroidTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyDroidTheme {
                // TODO - to use NavigationSuiteScaffold
                val windowSizeClass = calculateWindowSizeClass(this)
                when (windowSizeClass.widthSizeClass) {
                    WindowWidthSizeClass.Compact -> {
                        MainAppPortrait()
                    }

                    WindowWidthSizeClass.Expanded -> {
                        MainAppLandscape()
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = "",
            onValueChange = {}
        )
        Spacer(modifier = Modifier.height(16.dp))
        CardExpansion(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            title = "Hello",
            subtitle = "World",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.".repeat(
                2
            ),
            onIconClick = { expanded = !expanded },
            expanded = expanded
        )
    }
}

@Composable
fun MainAppLandscape() {
    StudyDroidTheme {
        Row {
            LeftNavigationRail()
            MainScreen()
        }

    }
}

@Composable
fun MainAppPortrait() {
    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { padding ->
        MainScreen(
            modifier = Modifier.padding(padding)
        )
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
private fun MainScreenPortraitPreview() {
    MainAppPortrait()

}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
private fun MainScreenLandscapePreview() {
    StudyDroidTheme {
        MainAppLandscape()
    }
}