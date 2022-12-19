package com.tech.peachmedia.feature_media_posts.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.tech.peachmedia.feature_media_posts.presentation.theme.PeachAppTheme
import com.tech.peachmedia.feature_media_posts.presentation.theme.TopBar
import com.tech.peachmedia.feature_media_posts.presentation.viewmodel.PostsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val postsViewModel: PostsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postsViewModel.fetchRemotePost()
        postsViewModel.fetchRemoteUsers()
        setContent {
            MainScreen()
        }

    }

    @Composable
    fun MainScreen() {
        MaterialTheme {
            Surface(color = MaterialTheme.colors.background) {
                Scaffold(
                    topBar = { TopBar() }
                ) {
                    PostsListView()
                }
            }
        }
    }

}