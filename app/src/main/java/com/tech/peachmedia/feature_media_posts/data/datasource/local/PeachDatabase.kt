package com.tech.peachmedia.feature_media_posts.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tech.peachmedia.feature_media_posts.data.datasource.local.dao.CommentsDao
import com.tech.peachmedia.feature_media_posts.data.datasource.local.dao.PostsDao
import com.tech.peachmedia.feature_media_posts.data.datasource.local.dao.UsersDao
import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.Comment
import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.Post
import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.User

@Database(
    entities = [User::class, Post::class, Comment::class],
    version = 2

)
@TypeConverters(Converters::class)
abstract class PeachDatabase : RoomDatabase() {
    abstract val commentsDao: CommentsDao
    abstract val usersDao: UsersDao
    abstract val postsDao: PostsDao

    companion object {
        const val DATABASE_NAME = "peach_db"
    }
}
