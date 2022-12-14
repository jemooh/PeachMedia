package com.tech.peachmedia.feature_media_posts.data.repository

import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.Comment
import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.Post
import com.tech.peachmedia.feature_media_posts.presentation.model.PostView
import com.tech.peachmedia.feature_media_posts.data.datasource.remote.model.Result
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun fetchRemoteUsers(): Result<Boolean>
    suspend fun fetchRemoteMediaPost(): Result<Boolean>
    fun getAllPosts(): Flow<List<PostView>>
    fun getPosts(): Flow<List<Post>>
    fun getPostById(documentId: String?): Flow<PostView>
    fun filterPostByMediaType(mediaType: String): Flow<List<PostView>>
    fun getCommentsById(documentId: String?): Flow<List<Comment>>
    suspend fun updatePostUrl(documentId: String?,url:String)
}
