package com.codelab.mybasicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.mybasicscodelab.ui.theme.MyBasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBasicsCodelabTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    var shouldShowOnBoarding by remember { mutableStateOf(true) }

    if (shouldShowOnBoarding) {
        OnBoardingScreen(onContinueClicked = { shouldShowOnBoarding = false })
    } else {
        Greetings()
    }
}

@Composable
fun OnBoardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(modifier = Modifier.padding(24.dp), onClick = onContinueClicked) {
                Text(text = "Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnBoardingPreview() {
    MyBasicsCodelabTheme {
        OnBoardingScreen(onContinueClicked = {})
    }
}

@Composable
fun Greetings(names: List<String> = List(1000) { "$it" }) {
    // same as RecyclerView
    LazyColumn(modifier = Modifier.padding(4.dp)) {
        items(items = names) { Greeting(name = it) }
    }
}

@Preview(showBackground = true, name = "Text Preview", widthDp = 320)
@Composable
fun GreetingsPreview() {
    MyBasicsCodelabTheme {
        Greetings()
    }
}

@Composable
fun Greeting(name: String) {
    // if expanded state is changed, will be recomposed
    // btw state won't be preserved when config changed
    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) 48.dp else 0.dp
    Surface(
        color = MaterialTheme.colors.primary, modifier = Modifier.padding(
            vertical = 4.dp,
            horizontal = 8.dp
        )
    ) {
        // use text on top of it as onPrimary
        Row(modifier = Modifier.padding(24.dp)) {
            // weight fills all available space -> removed fillMaxWidth
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello ")
                Text(text = "$name!")
            }
            OutlinedButton(onClick = { expanded.value = !expanded.value }) {
                Text(text = if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}