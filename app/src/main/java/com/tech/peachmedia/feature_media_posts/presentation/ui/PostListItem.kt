package com.tech.peachmedia.feature_media_posts.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.tech.peachmedia.R
import com.tech.peachmedia.feature_media_posts.domain.utils.Constants
import com.tech.peachmedia.feature_media_posts.domain.utils.Util
import com.tech.peachmedia.feature_media_posts.presentation.model.PostView
import timber.log.Timber


@Composable
fun PostListItem(postView: PostView) {
    var viewcomments by remember { mutableStateOf(false) }
    if (viewcomments) {
        //ViewComments(postView.comments)
    } else {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .clickable {},
            elevation = 6.dp
        ) {
            Column(
                modifier = Modifier.padding(15.dp)
            ) {
                Text(
                    text = Util.capitalizeString(postView.username.toString()),
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.height(4.dp))

                if (postView.mediaType == Constants.mediaType.PHOTO) {
                    ImageCard(postView.url.toString())
                } else {
                    VideoCard(postView.url.toString())
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = postView.caption.toString(),
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { },
                ) {
                    Text(
                        text = Util.formatDateTimeWithMsToReadableFormat(postView.createdAt.toString()),
                        style = MaterialTheme.typography.body1,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(top = 8.dp)
                    )

                    if (postView.comments.isNotEmpty()){
                        Text(
                            text = String.format(stringResource(R.string.tv_view_all_comments), postView.comments.count()),
                            style = MaterialTheme.typography.body1,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .clickable {
                                    viewcomments = true
                                }
                        )
                    }else{
                        Text(
                            text = String.format(stringResource(R.string.tv_comments), postView.comments.count()),
                            style = MaterialTheme.typography.body1,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .clickable {
                                    viewcomments = false
                                }
                        )
                    }



                }

            }
        }
    }
}