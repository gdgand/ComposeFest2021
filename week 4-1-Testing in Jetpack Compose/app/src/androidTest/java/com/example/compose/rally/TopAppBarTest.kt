package com.example.compose.rally

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule

import com.example.compose.rally.ui.components.RallyTopAppBar
import org.junit.Rule
import org.junit.Test
import java.util.*

/**
 * TopAppBarTest.kt
 * Rally
 */

class TopAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

//    @Test
//    fun rallyTopAppBarTest_currentLabelExists() {
//        val allScreens = RallyScreen.values().toList()
//        composeTestRule.setContent {
//            RallyTopAppBar(
//                allScreens = allScreens,
//                onTabSelected = { },
//                currentScreen = RallyScreen.Accounts
//            )
//        }
//
//        composeTestRule
//            .onNode(
//                hasText(RallyScreen.Accounts.name.uppercase(Locale.getDefault())) and
//                        hasParent(
//                            hasContentDescription(RallyScreen.Accounts.name)
//                        ),
//                useUnmergedTree = true
//            )
//            .assertExists()
//    }

    @Test
    fun rallyTopAppBarTest_clickTabs(){
        var currentScreen:RallyScreen = RallyScreen.Overview // 현재 상태
        composeTestRule.setContent {
            RallyApp(currentScreen){ screen-> currentScreen = screen }
        }

        RallyScreen.values().forEach { screen->
            composeTestRule
                .onNodeWithContentDescription(screen.name)
                .performClick()
            assert(currentScreen == screen)
        }
    }
}