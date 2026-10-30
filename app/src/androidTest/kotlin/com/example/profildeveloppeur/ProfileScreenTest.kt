package com.example.profildeveloppeur

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun profileScreen_displaysNameAndTriggersAction() {
        var clicked = false

        composeTestRule.setContent {
            ProfilDeveloppeurTheme {
                DeveloperProfileScreen(
                    name = "Alexandre Martin",
                    role = "Développeur Android Junior",
                    description = "Description de test",
                    skills = listOf("Kotlin", "Compose"),
                    availability = "Disponible",
                    onActionClick = { clicked = true }
                )
            }
        }

        composeTestRule.onNodeWithText("Alexandre Martin").assertExists()
        composeTestRule.onNodeWithText("Contacter").performClick()
        assertTrue(clicked)
    }
}
