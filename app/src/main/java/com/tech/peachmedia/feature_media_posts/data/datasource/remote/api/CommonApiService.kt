package com.tech.peachmedia.feature_media_posts.data.datasource.remote.api

import com.tech.peachmedia.feature_media_posts.data.datasource.remote.model.PostsResponse
import com.tech.peachmedia.feature_media_posts.data.datasource.remote.model.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface CommonApiService {
    @GET("posts")
    suspend fun getMediaPosts(): Response<PostsResponse>

    @GET("users")
        suspend fun getUsers(): Response<UserResponse>
}
