package com.skyapp.newsapp.ui.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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



fun newsAppFadeIn(): EnterTransition {
    return fadeIn(
        animationSpec = tween(
            300,
            easing = LinearEasing
        )
    )
}


fun newsAppFadeOut(): ExitTransition {
    return fadeOut(
        animationSpec = tween(
            300, easing = LinearEasing
        )
    )
}




fun mapIconName(name: String) : ImageVector {
   return   when(name.lowercase()){
        "home" -> Icons.Default.Home
        "search" -> Icons.Default.Search
        "bookmark" -> Icons.Default.Bookmark
        "profile" -> Icons.Default.PersonOutline
        else -> Icons.Default.Info
    }
}

fun currentActiveDestination(name: String) : String {
   return when(name) {
       "NewsHomeScreen" ->  "NewsHomeScreen"
       "NewsArticleScreen" ->"NewsArticleScreen"
       "NewsDetailScreen" -> "NewsDetailScreen"
       else -> ""
   }
}

