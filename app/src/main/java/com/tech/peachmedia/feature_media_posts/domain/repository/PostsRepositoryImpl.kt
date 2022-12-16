package com.tech.peachmedia.feature_media_posts.domain.repository

import com.tech.peachmedia.feature_media_posts.data.datasource.local.dao.CommentsDao
import com.tech.peachmedia.feature_media_posts.data.datasource.local.dao.PostsDao
import com.tech.peachmedia.feature_media_posts.data.datasource.local.dao.UsersDao
import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.Comment
import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.Post
import com.tech.peachmedia.feature_media_posts.data.datasource.remote.api.CommonApiService
import com.tech.peachmedia.feature_media_posts.data.repository.PostsRepository
import com.tech.peachmedia.feature_media_posts.presentation.model.PostView
import java.io.IOException
import java.util.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import timber.log.Timber

internal class PostsRepositoryImpl(
    private val commonApiService: CommonApiService,
    private val usersDao: UsersDao,
    private val commentsDao: CommentsDao,
    private val postsDao: PostsDao,
    private val isDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PostsRepository {
    override suspend fun fetchRemoteUsers(): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchRemoteMediaPost(): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override fun getPostComments(documentId: String): Flow<List<Comment>> {
        return commentsDao.getPostComments(documentId)
    }

    override fun getAllPosts(): Flow<List<PostView>> {
        return postsDao.getAllPosts()
    }

    override fun filterPostByMediaType(mediaType: String): Flow<List<PostView>> {
        return postsDao.filterPostByMediaType(mediaType)
    }


}
