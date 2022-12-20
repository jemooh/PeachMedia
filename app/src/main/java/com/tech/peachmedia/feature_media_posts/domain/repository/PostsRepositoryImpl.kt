package com.tech.peachmedia.feature_media_posts.domain.repository

import com.tech.peachmedia.feature_media_posts.data.datasource.local.dao.CommentsDao
import com.tech.peachmedia.feature_media_posts.data.datasource.local.dao.PostsDao
import com.tech.peachmedia.feature_media_posts.data.datasource.local.dao.UsersDao
import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.Comment
import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.Post
import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.User
import com.tech.peachmedia.feature_media_posts.data.datasource.remote.model.Result
import com.tech.peachmedia.feature_media_posts.data.datasource.remote.api.CommonApiService
import com.tech.peachmedia.feature_media_posts.data.repository.PostsRepository
import com.tech.peachmedia.feature_media_posts.domain.utils.Util
import com.tech.peachmedia.feature_media_posts.presentation.model.PostView
import java.io.IOException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
        return withContext(isDispatcher) {
            try {
                val result = commonApiService.getMediaPosts()
                if (result.isSuccessful) {
                    val remotePosts = result.body()
                    remotePosts?.documents?.forEach { postDocuments ->
                        //cache posts
                        val post = Post(
                            documentId = postDocuments.fields?.id?.stringValue,
                            authorID = postDocuments.fields?.authorID?.stringValue,
                            mediaType = postDocuments.fields?.mediaType?.stringValue,
                            storageRef = postDocuments.fields?.storageRef?.stringValue,
                            caption = postDocuments.fields?.caption?.stringValue,
                            createdAt = postDocuments.fields?.createdAt?.timestampValue,
                            createTime = postDocuments.createTime,
                            updateTime = postDocuments.updateTime
                        )
                        postsDao.insertAsync(post)

                        postDocuments.fields?.comments?.arrayValue?.values?.forEach { remoteComment ->
                            val comment = Comment(
                                documentId = postDocuments.fields?.id?.stringValue,
                                comment = remoteComment.stringValue.toString()
                            )
                            commentsDao.insertAsync(comment)
                        }

                    }

                    Result.Success(true)
                } else {
                    Result.Success(false)
                    Result.Error(Exception(result.errorBody().toString()))
                }
            } catch (e: IOException) {
                Result.Error(Exception("Error Occurred"))
                e.printStackTrace()
                Timber.e(e)
            }
            Result.Success(false)
        }
    }

    override suspend fun fetchRemoteMediaPost(): Result<Boolean> {
        return withContext(isDispatcher) {
            try {
                val result = commonApiService.getUsers()
                if (result.isSuccessful) {
                    val remoteUsers = result.body()
                    remoteUsers?.documents?.forEach { userDocuments ->
                        //cache posts
                        val user = User(
                            userId = Util.getUserIdString(userDocuments.name),
                            username = userDocuments.fields?.username?.stringValue,
                            email = userDocuments.fields?.email?.stringValue,
                            createTime = userDocuments.createTime,
                            updateTime = userDocuments.updateTime
                        )
                        usersDao.insertAsync(user)
                    }

                    Result.Success(true)
                } else {
                    Result.Success(false)
                    Result.Error(Exception(result.errorBody().toString()))
                }
            } catch (e: IOException) {
                Result.Error(Exception("Error Occurred"))
                e.printStackTrace()
                Timber.e(e)
            }
            Result.Success(false)
        }
    }

    override fun getAllPosts(): Flow<List<PostView>> {
        return postsDao.getAllPosts().map { it ->
            it.map {
                it.comments = getPostComments(it.documentId)
            }
            it
        }
    }

    override fun getPostById(documentId: String?): Flow<PostView> {
        return postsDao.getPostById(documentId)
    }

    private fun getPostComments(documentId: String?): List<Comment> {
        return commentsDao.getPostComments(documentId)
    }

    override fun filterPostByMediaType(mediaType: String): Flow<List<PostView>> {
        return postsDao.filterPostByMediaType(mediaType).map { it ->
            it.map {
                it.comments = getPostComments(it.documentId)
            }
            it
        }
    }

}
