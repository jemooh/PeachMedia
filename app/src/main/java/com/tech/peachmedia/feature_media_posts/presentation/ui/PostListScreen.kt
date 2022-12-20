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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.firebase.FirebaseApp
import com.google.firebase.storage.FirebaseStorage
import com.tech.peachmedia.feature_media_posts.domain.utils.Constants
import com.tech.peachmedia.feature_media_posts.domain.utils.Util.formatDateTimeWithMsToReadableFormat
import com.tech.peachmedia.feature_media_posts.presentation.model.PostView
import com.tech.peachmedia.feature_media_posts.presentation.theme.PeachAppTheme
import com.tech.peachmedia.feature_media_posts.presentation.viewmodel.PostsViewModel
import org.koin.androidx.compose.getViewModel
import timber.log.Timber


@Preview(showSystemUi = true, showBackground = true)
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
                textAlign = TextAlign.Left,
                text = "Peach posts list is Empty"
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
fun VideoCard(videoUrl: String) {
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(LocalContext.current)
        .build()
        .also { exoPlayer ->
            val mediaItem = MediaItem.Builder()
                .setUri("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4")
                .build()
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
        }

    DisposableEffect(
        AndroidView(factory = {
            StyledPlayerView(context).apply {
                player = exoPlayer
            }
        })
    ) {
        onDispose { exoPlayer.release() }
    }
}

@Composable
fun ImageCard(imageUrl: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://pbs.twimg.com/profile_images/1507434537395048457/IG9S2Na3_400x400.jpg")
            .crossfade(true)
            .build(),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    )
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
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                text = postView.username.toString(),
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            if (postView.mediaType == Constants.mediaType.PHOTO) {
                ImageCard(postView.storageRef.toString())
            } else {
                VideoCard(postView.storageRef.toString())
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = postView.caption.toString(),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(top = 8.dp)
            )

            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = formatDateTimeWithMsToReadableFormat(postView.createdAt.toString()),
                    style = MaterialTheme.typography.body1,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )

                Text(
                    text = String.format("%s Comments", postView.comments.count()),
                    style = MaterialTheme.typography.body1,
                    fontSize = 12.sp
                    ,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable {

                        }
                )


            }


        }
    }
}
