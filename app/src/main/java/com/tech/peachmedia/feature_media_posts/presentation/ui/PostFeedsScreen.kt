package com.tech.peachmedia.feature_media_posts.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.tech.peachmedia.R
import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.Comment
import com.tech.peachmedia.feature_media_posts.domain.utils.Constants
import com.tech.peachmedia.feature_media_posts.domain.utils.Util
import com.tech.peachmedia.feature_media_posts.domain.utils.Util.formatDateTimeWithMsToReadableFormat
import com.tech.peachmedia.feature_media_posts.presentation.model.PostView
import com.tech.peachmedia.feature_media_posts.presentation.viewmodel.PostsViewModel
import org.koin.androidx.compose.getViewModel
import timber.log.Timber


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PostsFeedsScreen() {
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            FilterView()
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
}

@Composable
fun FilterView() {
    val postsViewModel = getViewModel<PostsViewModel>()
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("all", "photo", "video")
    var selectedText by remember { mutableStateOf("") }

    val icon = if (expanded)
        Icons.Filled.ArrowDropUp //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.ArrowDropDown


    Column {
        Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = { selectedText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                label = { Text(stringResource(R.string.tv_filter_feeds)) },
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable { expanded = !expanded })
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                suggestions.forEach { option ->
                    DropdownMenuItem(onClick = {
                        selectedText = option
                        expanded = false
                        when (option) {
                            Constants.mediaType.PHOTO -> {
                                postsViewModel.filterPostByMediaType(Constants.mediaType.PHOTO)

                            }

                            Constants.mediaType.VIDEO -> {
                                postsViewModel.filterPostByMediaType(Constants.mediaType.VIDEO)
                            }

                            else -> {
                                postsViewModel.getAllPosts()
                            }
                        }
                    }) {
                        Text(text = option)
                    }
                }
            }
        }
    }
}





