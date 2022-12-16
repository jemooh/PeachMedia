package com.tech.peachmedia.feature_media_posts.data.datasource.remote.model

/**
 * Sealed class for networking and UI states
 */
sealed class Result<out T : Any> {
    object Loading : Result<Nothing>()
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}
