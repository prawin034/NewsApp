package com.skyapp.newsapp.ui.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


/*
val dateTime = LocalDateTime.parse(
                                clientData.createdDate,
                                DateTimeFormatter.ISO_DATE_TIME
                            )
                            val formattedDate =
                                dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
 */

@RequiresApi(Build.VERSION_CODES.O)
fun parseValidDateString(date: String?) : String {
     if(date?.isEmpty() == true) return date

    val date = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH)
    return date.format(formatter)

}














