package io.lab27.week2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import coil.compose.rememberImagePainter
import io.lab27.week2.ui.theme.Week2Theme


class TestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week2Theme {
                LayoutsCodelab2()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LayoutsCodelab2() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        BodyContent2(Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent2(modifier: Modifier = Modifier) {
//    BoxWithConstraintsDemo()
    Row(modifier = modifier) {
        Test(
            modifier
                .wrapContentHeight()
                .width(150.dp)
                .padding(10.dp), text = "가나다라마바사"
        )
        Test(
            modifier
                .wrapContentHeight()
                .width(150.dp)
                .padding(10.dp),
            text = "가나다라마바사가나다라마바사가나다라마바사가나다라마바사가나다라마바사"
        )
    }
}


@Composable
fun Test(modifier: Modifier = Modifier, text: String) {
    var currentHeight by remember { mutableStateOf(Size.Zero) }

    Card(modifier = modifier, shape = RoundedCornerShape(20.dp)) {
        val extraPadding = when {
            currentHeight.height > 300 -> 100.dp
            else -> 20.dp
        }

        Column(modifier = Modifier.padding(10.dp)) {
            Image(
                painter = rememberImagePainter(
                    data = "https://developer.android.com/images/brand/Android_Robot.png"
                ),
                contentDescription = "Android Logo",
                modifier = Modifier.size(50.dp)
            )
            Text(text = text,
                modifier = Modifier
                    .padding(bottom = extraPadding)
                    .onGloballyPositioned { textArea -> currentHeight = textArea.size.toSize() })
        }
    }

}


@Composable
fun BoxWithConstraintsDemo() {
    Column {
        Column {
            MyBoxWithConstraintsDemo()
        }

        Text("Here we set the size to 150.dp", modifier = Modifier.padding(top = 20.dp))
        Text("Here we set the size to 150.dp", modifier = Modifier.padding(top = 20.dp))
        Text("Here we set the size to 150.dp", modifier = Modifier.padding(top = 20.dp))
        Text("Here we set the size to 150.dp", modifier = Modifier.padding(top = 20.dp))
        Text("Here we set the size to 150.dp", modifier = Modifier.padding(top = 20.dp))
        Column(modifier = Modifier.wrapContentHeight()) {
            MyBoxWithConstraintsDemo()
        }
    }
}

@Composable
private fun MyBoxWithConstraintsDemo() {
    BoxWithConstraints {
        val boxWithConstraintsScope = this
        //You can use this scope to get the minWidth, maxWidth, minHeight, maxHeight in dp and constraints

        Column {
            if (boxWithConstraintsScope.maxHeight >= 200.dp) {
                Text(
                    "This is only visible when the maxHeight is >= 200.dp",
                    style = TextStyle(fontSize = 20.sp)
                )
            }
            Text("minHeight: ${boxWithConstraintsScope.minHeight}, maxHeight: ${boxWithConstraintsScope.maxHeight},  minWidth: ${boxWithConstraintsScope.minWidth} maxWidth: ${boxWithConstraintsScope.maxWidth}")
        }
    }
}