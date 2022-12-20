package com.tech.peachmedia.feature_media_posts.presentation.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.tech.peachmedia.R
import com.tech.peachmedia.feature_media_posts.domain.utils.Constants
import com.tech.peachmedia.feature_media_posts.presentation.theme.TopBar
import com.tech.peachmedia.feature_media_posts.presentation.viewmodel.PostsViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }


    @Composable
    fun MainScreen() {
        MaterialTheme {
            Surface(color = MaterialTheme.colors.background) {
                Scaffold(
                    topBar = { TopBar(title = stringResource(R.string.app_name)) }
                ) {
                    PostsFeedsScreen()
                }
            }
        }
    }

}