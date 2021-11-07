package com.codelab.mybasicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.mybasicscodelab.ui.theme.MyBasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBasicsCodelabTheme {
                myApp()
            }
        }
    }
}

@Composable
fun myApp() {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        Greeting("Android")
    }
}

@Composable
fun Greeting(name: String) {
    Surface(color = MaterialTheme.colors.primary) {
        // use text on top of it as onPrimary
        Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
    }
}

@Preview(showBackground = true, name = "Text Preview")
@Composable
fun DefaultPreview() {
    MyBasicsCodelabTheme {
        myApp()
    }
}