package com.tech.peachmedia.feature_media_posts.data.datasource.local.dao

import androidx.room.*
import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.Comment
import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentsDao : CoroutineBaseDao<Comment> {
    @Query("SELECT * FROM Comment WHERE documentId=:documentId ")
    fun getPostComments(documentId: String?):List<Comment>
}
