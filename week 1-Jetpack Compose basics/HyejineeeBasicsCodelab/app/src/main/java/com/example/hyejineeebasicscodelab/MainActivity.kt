package com.example.hyejineeebasicscodelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
fun Greetings(names: List<String>) {
    LazyColumn(
        modifier = Modifier.padding(vertical = 20.dp)
    ) {
        items(items = names){ name->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String) {

    var expended by rememberSaveable { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expended) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    val handleClick = {
        expended = !expended
    }

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp)
    ) {

        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello,")
                Text(text = "$name", style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                ))
            }

            OutlinedButton(onClick = handleClick) {
                Text(text = if (expended) "Show Less" else "Show More")
            }

        }

    }
}

@Composable
fun MyApp(names: List<String> = List<String>(1000){"$it"}) {
    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }
    val handleContinueClick = {
        shouldShowOnBoarding = false
    }
    Surface(color = MaterialTheme.colors.background) {
        if(shouldShowOnBoarding){
            OnBoardingScreen(handleContinueClick = handleContinueClick)
        }else{
            Greetings(names = names)
        }

    }
}

@Composable
fun OnBoardingScreen(handleContinueClick :()->Unit) {

    Surface {
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to the basics Codelab!")
            Button(
                onClick = handleContinueClick,
                modifier = Modifier.padding(24.dp),
            ) {
                Text(text = "Continue")
            }

        }
    }
}


@Preview(showBackground = true, name = "onBoarding", widthDp = 320, heightDp = 320)
@Composable
fun onBoardingPreview() {
    HyejineeeBasicsCodelabTheme {
        OnBoardingScreen({})
    }
}

@Preview(showBackground = true, name = "this is preview", widthDp = 320)
@Composable
fun DefaultPreview() {
    HyejineeeBasicsCodelabTheme {
        MyApp()
    }
}

@Preview(showBackground = true, name = "Dark Mode Preview", widthDp = 320, uiMode = UI_MODE_NIGHT_YES )
@Composable
fun DefaultPreviewDarkMode(){
    HyejineeeBasicsCodelabTheme {
        MyApp()
    }
}