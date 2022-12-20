package com.tech.peachmedia.feature_media_posts.presentation.ui

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso.LoadedFrom
import com.tech.peachmedia.feature_media_posts.presentation.theme.TopBar
import com.tech.peachmedia.feature_media_posts.presentation.viewmodel.PostsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    lateinit var storage: FirebaseStorage
    private val postsViewModel: PostsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* storage = Firebase.storage("gs://peach-assessment.appspot.com")
        var storageRef = storage.reference
        var spaceRef = storageRef.child("NEiFSfRshs7sIFVEtEQ9/9Gh48fyb6gVeY7cn2IT")


        Timber.d("Url:" + spaceRef?.downloadUrl)
*/

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