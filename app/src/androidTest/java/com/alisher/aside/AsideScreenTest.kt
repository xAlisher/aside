import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import org.junit.Rule
import org.junit.Test
import com.alisher.aside.ui.theme.AsideTheme
import com.alisher.aside.AsideScreen

class AsideScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun mainButtonsAreDisplayed() {
        composeTestRule.setContent {
            AsideTheme {
                AsideScreen(onCreate = {})
            }
        }

        composeTestRule.onNodeWithText("⎘ Paste").assertIsDisplayed()
        composeTestRule.onNodeWithText("+ Create").assertIsDisplayed()
    }
}
