package com.example.compose.rally

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.toUpperCase
import com.example.compose.rally.ui.components.RallyTopAppBar
import com.example.compose.rally.ui.theme.RallyTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUpTopAppBar() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTheme {
                var currentScreen by rememberSaveable { mutableStateOf(RallyScreen.Accounts) }
                RallyTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = { screen -> currentScreen = screen },
                    currentScreen = currentScreen
                )
            }
        }
    }

    @Test
    fun rallyTopAppBarTest_currentLabelExists() {
        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase()) and
                        hasParent(
                            hasContentDescription(RallyScreen.Accounts.name)
                        ),
                useUnmergedTree = true
            )
            .assertExists()
    }

    @Test
    fun rallyTopAppBarTest_changeSelectionWhenOtherTabClick() {
        composeTestRule
            .onNode(
                hasContentDescription(RallyScreen.Overview.name)
                , useUnmergedTree = true)
            .performClick()

        composeTestRule
            .onNode(
                hasText(RallyScreen.Overview.name.uppercase()) and
                        hasParent(
                            hasContentDescription(RallyScreen.Overview.name)
                        ),
                useUnmergedTree = true
            )
            .assertExists()
    }

}