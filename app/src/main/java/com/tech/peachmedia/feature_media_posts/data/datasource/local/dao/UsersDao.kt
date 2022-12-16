package com.tech.peachmedia.feature_media_posts.data.datasource.local.dao

import androidx.room.*
import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.Post
import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao : CoroutineBaseDao<User> {
    @Query("SELECT * FROM User ")
    fun getAllUsers(): Flow<List<User>>
}
