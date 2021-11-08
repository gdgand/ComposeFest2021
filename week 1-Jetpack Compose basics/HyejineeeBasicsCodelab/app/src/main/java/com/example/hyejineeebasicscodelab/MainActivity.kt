package com.example.hyejineeebasicscodelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String, expanded:Boolean = false) {

    var expanded by rememberSaveable { mutableStateOf(expanded) }

    Card(
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardGreeting(name = name, expanded = expanded)
    }


}

@Composable
fun CardGreeting(name:String, expanded: Boolean){
    var expanded by rememberSaveable{ mutableStateOf(expanded)}

    Row(
        modifier = Modifier
            .padding(24.dp)
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
                .padding(12.dp)
        ) {
            Text(text = "Hello,")
            Text(
                text = "$name", style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )

            if(expanded){
                Text(text = ("Composem ipsum color sit lazy, " +
                        "padding theme elit, sed do bouncy. ").repeat(4),
                    modifier = Modifier.padding(10.dp)
                )
            }
        }

        IconButton(
            onClick = { expanded = !expanded },
        ) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }

            )

        }

    }

}


@Composable
fun MyApp(
    names: List<String> = List<String>(1000) { "$it" },
    shouldShowOnBoarding: Boolean = true
) {
    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(shouldShowOnBoarding) }
    val handleContinueClick = {
        shouldShowOnBoarding = false
    }
    Surface(color = MaterialTheme.colors.background) {
        if (shouldShowOnBoarding) {
            OnBoardingScreen(handleContinueClick = handleContinueClick)
        } else {
            Greetings(names = names)
        }

    }
}

@Composable
fun OnBoardingScreen(handleContinueClick: () -> Unit) {

    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
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

@Preview(showBackground = true, name="ExpandedGreetingPreview", widthDp = 320, heightDp = 320)
@Composable
fun ExpandedGreetingPreview(){
    HyejineeeBasicsCodelabTheme() {
        Greeting(name = "hyejineee", true)
    }
}

@Preview(showBackground = true, name="DefaultGreetingPreview", widthDp = 320, heightDp = 150)
@Composable
fun DefaultGreetingPreview(){
    HyejineeeBasicsCodelabTheme() {
        Greeting(name = "hyejineee", false)
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
        MyApp(shouldShowOnBoarding = false)
    }
}

@Preview(
    showBackground = true,
    name = "Dark Mode Preview",
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun DefaultPreviewDarkMode() {
    HyejineeeBasicsCodelabTheme {
        MyApp(shouldShowOnBoarding = false)
    }
}