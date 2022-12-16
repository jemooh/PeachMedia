package com.tech.peachmedia.feature_media_posts.data.datasource.local.dao

import androidx.room.*
import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.Post
import com.tech.peachmedia.feature_media_posts.presentation.model.PostView
import kotlinx.coroutines.flow.Flow

@Dao
interface PostsDao : CoroutineBaseDao<Post> {
    @Query("SELECT *,user.username AS username FROM Post post JOIN User user ON user.id=post.authorID ")
    fun getAllPosts(): Flow<List<PostView>>

    @Query("SELECT * ,user.username AS username FROM Post post JOIN User user ON user.id=post.authorID WHERE mediaType=:mediaType")
    fun filterPostByMediaType(mediaType: String): Flow<List<PostView>>
}
