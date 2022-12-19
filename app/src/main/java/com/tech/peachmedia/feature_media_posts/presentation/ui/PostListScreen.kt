package com.tech.peachmedia.feature_media_posts.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tech.peachmedia.feature_media_posts.domain.utils.Constants
import com.tech.peachmedia.feature_media_posts.presentation.model.PostView
import com.tech.peachmedia.feature_media_posts.presentation.viewmodel.PostsViewModel
import org.koin.androidx.compose.getViewModel

@Preview
@Composable
fun PostsListView() {
    val postsViewModel = getViewModel<PostsViewModel>()
    val uiState = postsViewModel.state.collectAsState().value
    if (uiState.posts.isEmpty()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                text = "Peach post list is Empty"
            )
        }
    } else {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = uiState.posts,
                itemContent = {
                    PostListItem(postView = it)
                }
            )
        }
    }
}

@Composable
fun DisplayVideo(videoUrl: String) {

}

@Composable
fun DisplayImage(imageUrl: String) {

}

@Composable
fun PostListItem(postView: PostView) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable { },
        elevation = 6.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                text = postView.username.toString(),
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(top = 8.dp)
            )

            if (postView.mediaType == Constants.mediaType.PHOTO) {
                DisplayImage(postView.storageRef.toString())
            } else {
                DisplayVideo(postView.storageRef.toString())
            }

            Text(
                text = postView.caption.toString(),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(top = 8.dp)
            )

            Text(
                text = postView.createdAt.toString(),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(top = 8.dp)
            )
        }
    }
}
