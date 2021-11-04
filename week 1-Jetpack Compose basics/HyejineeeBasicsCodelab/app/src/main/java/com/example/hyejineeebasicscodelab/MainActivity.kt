package com.example.hyejineeebasicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hyejineeebasicscodelab.ui.theme.HyejineeeBasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HyejineeeBasicsCodelabTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val expended = remember{ mutableStateOf(false)}
    val extraPadding = if(expended.value) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp)
    ) {

        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier.weight(1f).padding(bottom = extraPadding)
            ) {
                Text(text = "Hello,")
                Text(text = "$name")
            }

            OutlinedButton(onClick = {expended.value = !expended.value}){
                Text(text = if(expended.value) "Show Less" else "Show More")
            }

        }

    }
}

@Composable
fun MyApp(names :List<String> = listOf("World", "Compose")) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.padding(4.dp)
        ) {
            for(name in names){
                Greeting(name = name)
            }
        }

    }
}

@Preview(showBackground = true, name = "this is preview", widthDp = 320)
@Composable
fun DefaultPreview() {
    HyejineeeBasicsCodelabTheme {
        MyApp()
    }
}