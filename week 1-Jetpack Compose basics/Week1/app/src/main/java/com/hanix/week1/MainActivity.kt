package com.hanix.week1

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanix.week1.ui.theme.Week1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week1Theme {
                // A surface container using the 'background' color from the theme
                MyApp()     // 중복 코드 방지
            }
        }
    }
}


@Composable
private fun MyApp() {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }       // 다시 온보딩이 되지 않게 함

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    } else {
        Greetings()
    }
}

@Composable
fun Greetings(names: List<String> = List(100) { "$it" }) {        // 1000 개의 item 생성
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Card (backgroundColor = MaterialTheme.colors.primary,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        CardContent(name)
    }
}

@Composable
private fun CardContent(name: String) {
    val expanded = remember { mutableStateOf(false) }       // 재구성을 방지하는데 사용

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,     // 애니메이션중단해도 자연스럽게 보임
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(modifier = Modifier
            .weight(1f)
            .padding(12.dp)) {
            Text("Hello, ")
            Text(text = name,
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold))

            if(expanded.value) {
                Text(text = ("Composem ipsum color sit lazy, " +
                        "padding theme elit, sed do bouncy. ").repeat(4))       // 4 번 반복
            }
        }
        IconButton(onClick = { expanded.value = !expanded.value }) {
            Icon(
                imageVector = if (expanded.value) Filled.ExpandLess else Filled.ExpandMore,
                contentDescription = if (expanded.value) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }

            )
        }
    }
}


@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize()
            // gravity
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "WelCome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text(text = "Continue")
            }
        }
    }
}


@Preview(
    showBackground = true,
    name = "Preview Night",
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    showBackground = true,
    name = "Preview Light",
    widthDp = 320
)
@Composable
fun DefaultPreview() {
    Week1Theme {
        Greetings()
    }
}
