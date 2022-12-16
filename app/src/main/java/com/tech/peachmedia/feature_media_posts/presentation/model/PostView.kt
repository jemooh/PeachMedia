package com.tech.peachmedia.feature_media_posts.presentation.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["documentId"], unique = true)])
data class PostView(
    @PrimaryKey
    val documentId: String,
    val authorID: String,
    val mediaType: String,
    val storageRef: String,
    val caption: String,
    val username: String,
    val createdAt: String,
    val createTime: String,
    val updateTime: String
)

