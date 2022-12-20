package com.tech.peachmedia.feature_media_posts.presentation.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.tech.peachmedia.feature_media_posts.data.datasource.local.model.Comment
import com.tech.peachmedia.feature_media_posts.data.datasource.remote.model.Comments

data class PostView(
    var documentId: String = "",
    var authorID: String? = null,
    var mediaType: String? = null,
    var storageRef: String? = null,
    var url: String? = null,
    var caption: String? = null,
    var username: String? = null,
    var createdAt: String? = null,
    var createTime: String? = null,
    var updateTime: String? = null,
    @Ignore
    var comments: List<Comment> = emptyList()
)

