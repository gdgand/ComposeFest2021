package com.example.compose.rally

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.compose.rally.ui.overview.OverviewBody
import com.example.compose.rally.ui.theme.RallyTheme
import org.junit.Rule
import org.junit.Test

class OverviewScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun overviewScreen_alertDisplayed() {
        composeTestRule.setContent {
            RallyTheme {
                OverviewBody()
            }
        }

        composeTestRule
            .onNodeWithText("Alerts")
            .assertIsDisplayed()
    }
}