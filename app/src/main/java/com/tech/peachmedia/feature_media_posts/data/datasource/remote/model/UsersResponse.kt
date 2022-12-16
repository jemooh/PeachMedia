package com.tech.peachmedia.feature_media_posts.data.datasource.remote.model

data class UserResponse(
    val documents: UserDocuments? = null
)

data class UserDocuments(
    val name: String? = null,
    val createTime: String? = null,
    val updateTime: String? = null,
    val fields: Fields? = null,
)

data class Fields(
    val username: Username? = null,
    val email: Email? = null
)

data class Username(
    val stringValue: String
)

data class Email(
    val stringValue: String
)
