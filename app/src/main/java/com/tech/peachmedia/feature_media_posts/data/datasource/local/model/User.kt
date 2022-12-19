package com.tech.peachmedia.feature_media_posts.data.datasource.local.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["userId"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val userId: String,
    val username: String? = null,
    val email: String? = null,
    val createTime: String? = null,
    val updateTime: String? = null
)

