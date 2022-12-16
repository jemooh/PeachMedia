package com.tech.peachmedia.feature_media_posts.data.datasource.local.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["id"], unique = true)])
data class User(
    @PrimaryKey
    val id: String,
    val username: String,
    val email: String,
    val createTime: String,
    val updateTime: String
)

