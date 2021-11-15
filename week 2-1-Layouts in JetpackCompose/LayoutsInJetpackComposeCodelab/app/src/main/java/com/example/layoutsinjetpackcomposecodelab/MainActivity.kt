package com.example.layoutsinjetpackcomposecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.layoutsinjetpackcomposecodelab.ui.theme.LayoutsInJetpackComposeCodelabTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutsInJetpackComposeCodelabTheme {
                SimpleList()
            }
        }
    }
}

@Composable
fun LayoutsCodelab() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Layout Codelab")
                },
                actions = {
                    Row() {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Filled.Favorite, contentDescription = null)
                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Filled.AccountBox, contentDescription = null)
                        }
                    }

                }
            )
        }
    ) { innerPadding ->
        BodyContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
    }
}

@Preview
@Composable
fun LayoutsCodelabPreview() {
    LayoutsInJetpackComposeCodelabTheme() {
        LayoutsCodelab()
    }
}

@Composable
fun PhotographerCard() {
    Row(modifier = Modifier
        .padding(8.dp)                              // margin처럼 사용할 수 있음.
        .clip(RoundedCornerShape(4.dp))
        .background(MaterialTheme.colors.surface)
        .clickable { }
        .padding(16.dp)
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {

        }

        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp)
        ) {
            Text(text = "Alfred Sisley", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = "3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Preview(name = "PhotographerCartPreview")
@Composable
fun PhotographerCartPreview() {
    LayoutsInJetpackComposeCodelabTheme {
        PhotographerCard()
    }
}

@Composable
fun SimpleList() {
    val itemSize = 10000
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column() {
        Row(modifier = Modifier.padding(6.dp).fillMaxWidth()) {
            Button(
                onClick = { coroutineScope.launch { scrollState.animateScrollToItem(0) }},
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Scroll to the top")
            }

            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = { coroutineScope.launch { scrollState.animateScrollToItem(itemSize-1) }},
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Scroll to the bottom")
            }
        }
        LazyColumn(state = scrollState) {
            items(10000) {
//            Surface(
//                modifier = Modifier
//                    .background(MaterialTheme.colors.surface)
//                    .fillParentMaxWidth()
//            ) {
//                Text(text = "Item$it", modifier = Modifier.padding(5.dp))
//            }
                SimpleListItem(index = it)
            }
        }
    }


}

@Preview(name = "SimpleListPreview")
@Composable
fun SimpleListPreview() {
    LayoutsInJetpackComposeCodelabTheme {
        SimpleList()
    }
}

@Composable
fun SimpleListItem(index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(6.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colors.surface)
            .padding(10.dp),
    ) {
        Image(
            painter = rememberImagePainter(data = "https://developer.android.com/images/brand/Android_Robot.png"),
            contentDescription = "android logo",
            modifier = Modifier.size(50.dp),
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column() {
            Text(text = "#ITEM $index", fontWeight = FontWeight.Bold)
            Text(text = "this is ${index}st item")
        }
    }
}

@Preview(name = "SimpleListItemPreview")
@Composable
fun SimpleListItemPreview() {
    LayoutsInJetpackComposeCodelabTheme {
        SimpleListItem(0)
    }
}