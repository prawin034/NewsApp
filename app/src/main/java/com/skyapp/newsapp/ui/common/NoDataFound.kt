package com.skyapp.newsapp.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.rememberLottieComposition
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants

@Composable
fun NoDataFound(
    text: String = "Can't find exact matches for your search. \n" +
            " Try searching for other names."
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        NoDataFoundAnimation()
        Text(
            text = text,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )


    }
}


@Composable
fun NoDataFoundAnimation() {


    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(com.skyapp.newsapp.R.raw.nodatafoundanimation))


    val clipSpec = LottieClipSpec.Progress(0f, 1f)

    com.airbnb.lottie.compose.LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        contentScale = ContentScale.Fit,
        clipSpec = clipSpec,
        speed = 1.1f,
        reverseOnRepeat = true,
        isPlaying = true,
        modifier = Modifier.size(200.dp)


    )


}