package com.tech.peachmedia.feature_media_posts.data.datasource.local.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["documentId"], unique = true)])
data class Post(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val documentId: String?=null,
    val authorID: String? = null,
    val mediaType: String? = null,
    val storageRef: String? = null,
    val caption: String? = null,
    val createdAt: String? = null,
    val createTime: String? = null,
    val updateTime: String? = null
)

