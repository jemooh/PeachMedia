package com.tech.peachmedia.feature_media_posts.data.repository

import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.Comment
import com.tech.peachmedia.feature_media_posts.presentation.model.PostView
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun fetchRemoteUsers(): Result<Boolean>
    suspend fun fetchRemoteMediaPost(): Result<Boolean>
    fun getPostComments(documentId: String): Flow<List<Comment>>
    fun getAllPosts(): Flow<List<PostView>>
    fun filterPostByMediaType(mediaType: String): Flow<List<PostView>>
}
