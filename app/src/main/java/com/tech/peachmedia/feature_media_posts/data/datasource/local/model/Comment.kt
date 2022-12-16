package com.tech.peachmedia.feature_media_posts.data.datasource.local.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["comment"], unique = true)])
data class Comment(
    @PrimaryKey
    val comment: String,
    val documentId: String,
    val updateTime: String,
)

