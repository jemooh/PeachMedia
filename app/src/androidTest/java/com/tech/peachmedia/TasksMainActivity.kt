package com.tech.peachmedia

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tech.peachmedia.feature_media_posts.domain.utils.Constants.BASE_URL
import com.tech.peachmedia.feature_media_posts.domain.utils.Constants.mediaType.PHOTO
import com.tech.peachmedia.feature_media_posts.presentation.ui.MainActivity
import com.tech.peachmedia.feature_media_posts.presentation.ui.PostListItem
import com.tech.peachmedia.feature_media_posts.presentation.ui.PostsFeedsScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TasksMainActivity {

    @get:Rule

    val composeTestRule= createAndroidComposeRule<MainActivity>()


    @Test
    fun testFilterTextField(){
        val filterPhoto="photo"
        composeTestRule.onNodeWithTag("Filter feeds").performTextInput(filterPhoto)
        composeTestRule.onNodeWithTag("Filter feeds").assertTextContains(filterPhoto)
    }
}