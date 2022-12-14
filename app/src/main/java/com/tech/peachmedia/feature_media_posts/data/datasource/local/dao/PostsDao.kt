package com.tech.peachmedia.feature_media_posts.data.datasource.local.dao

import androidx.room.*
import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.Post
import com.tech.peachmedia.feature_media_posts.presentation.model.PostView
import kotlinx.coroutines.flow.Flow

@Dao
interface PostsDao : CoroutineBaseDao<Post> {
    @Query("SELECT * FROM Post  ")
    fun getPosts(): Flow<List<Post>>

    @Query("SELECT post.documentId,post.authorID,post.mediaType,post.storageRef,post.url,post.caption,user.username,post.createdAt,post.createTime,post.updateTime FROM Post post JOIN User user ON user.userId=post.authorID ")
    fun getAllPosts(): Flow<List<PostView>>

    @Query("SELECT post.documentId,post.authorID,post.mediaType,post.storageRef,post.url,post.caption,user.username,post.createdAt,post.createTime,post.updateTime FROM Post post JOIN User user ON user.userId=post.authorID WHERE post.documentId=:documentId ")
    fun getPostById(documentId: String?): Flow<PostView>

    @Query("SELECT  post.documentId,post.authorID,post.mediaType,post.storageRef,post.url,post.caption,user.username,post.createdAt,post.createTime,post.updateTime FROM Post post JOIN User user ON user.userId=post.authorID WHERE mediaType=:mediaType")
    fun filterPostByMediaType(mediaType: String): Flow<List<PostView>>

    @Query("UPDATE Post SET url = :url WHERE documentId =:documentId ")
    fun updatePostUrl(documentId: String?, url: String?)
}
