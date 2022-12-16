package com.tech.peachmedia.feature_media_posts.data.datasource.local.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["documentId"], unique = true)])
data class Post(
    @PrimaryKey
    val documentId: String,
    val authorID: String,
    val mediaType: String,
    val storageRef: String,
    val caption: String,
    val createdAt: String,
    val createTime: String,
    val updateTime: String
)

