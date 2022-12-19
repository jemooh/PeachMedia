package com.tech.peachmedia.feature_media_posts.domain.utils

object Util {
    fun getUserIdString(str: String?): String {
        return try {
            str?.split("/")?.map { it.trim() }?.last() ?:""
        } catch (e: Exception) {
            ""
        }
    }
}