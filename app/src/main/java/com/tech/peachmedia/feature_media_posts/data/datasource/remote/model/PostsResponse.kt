package com.tech.peachmedia.feature_media_posts.data.datasource.remote.model

data class PostsResponse(
    val documents: PostDocuments? = null,
)

data class PostDocuments(
    val name: String? = null,
    val createTime: String? = null,
    val updateTime: String? = null,
    val fields: PostFields? = null,
)

data class PostFields(
    val id: Id? = null,
    val authorID: AuthorID? = null,
    val caption: Caption? = null,
    val mediaType: MediaType? = null,
    val storageRef: StorageRef? = null,
    val createdAt: CreatedAt? = null,
    val comments: Comments? = null
)

data class Id(
    val stringValue: String
)

data class AuthorID(
    val stringValue: String
)

data class Caption(
    val stringValue: String
)

data class MediaType(
    val stringValue: String
)

data class StorageRef(
    val stringValue: String
)

data class CreatedAt(
    val timestampValue: String
)

data class Comments(
    val arrayValue: ArrayValue? = null
)

data class ArrayValue(
    val values: List<String>? = null
)


