package com.tech.peachmedia.feature_media_posts.domain.utils

import android.Manifest

object Constants {
    const val BASE_URL = "https://firestore.googleapis.com/v1/projects/peach-assessment/databases/(default)/documents/"
    const val STORAGE_BASE_URL = "gs://peach-assessment.appspot.com/"

    const val SIMPLE_DATE_TIME_MS_FORMAT_WITH_TIME_ZONE = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val SIMPLE_READABLE_DAY_DATE_TIME_FORMAT_AM_PM = "dd MMM, yyyy | HH:mm a"
    const val EMPTY_STRING=""

    object mediaType {
        const val PHOTO = "photo"
        const val VIDEO = "video"
    }
}
