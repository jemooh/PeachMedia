package com.tech.peachmedia.feature_media_posts.presentation.ui

import android.graphics.Color
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.Comment
import com.tech.peachmedia.feature_media_posts.presentation.theme.TopBar
import com.tech.peachmedia.feature_media_posts.presentation.viewmodel.PostsViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CommentsView(documentId: String?) {
    val contextForToast = LocalContext.current.applicationContext
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    val postsViewModel = getViewModel<PostsViewModel>()
    postsViewModel.getCommentById(documentId)
    val uiState = postsViewModel.state.collectAsState().value


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 56.dp,
        sheetContent = {

            Column() {
                /* Text(
                     text = "Comments")
                 )*/

            }
            LazyColumn {
                items(
                    items = uiState.comments,
                    itemContent = {
                        ViewComments(comment = it)
                    }
                )
            }

        }) {
    }

}


@Composable
fun ViewComments(comment: Comment) {
    Column {
        Column {
            Text(
                text = comment.comment, style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Left
            )
        }
    }
}