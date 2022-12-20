package com.tech.peachmedia.feature_media_posts.domain.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Util {
    fun getUserIdString(str: String?): String {
        return try {
            str?.split("/")?.map { it.trim() }?.last() ?:""
        } catch (e: Exception) {
            ""
        }
    }


    fun formatDateTimeWithMsToReadableFormat(timeString: String): String {
        val msDateFormat = SimpleDateFormat(
            Constants.SIMPLE_DATE_TIME_MS_FORMAT_WITH_TIME_ZONE,
            Locale.getDefault()
        )
        val targetFormat = SimpleDateFormat(
            Constants.SIMPLE_READABLE_DAY_DATE_TIME_FORMAT_AM_PM,
            Locale.getDefault()
        )
        return try {
            val date = msDateFormat.parse(timeString)
            targetFormat.format(date)
        } catch (ex: ParseException) {
            ex.printStackTrace()
            Constants.EMPTY_STRING
        }
    }

    fun capitalizeString(str: String): String {
        var retStr = str
        try { // We can face index out of bound exception if the string is null
            retStr = str.substring(0, 1).toUpperCase() + str.substring(1)
        } catch (e: Exception) {
        }
        return retStr
    }
}