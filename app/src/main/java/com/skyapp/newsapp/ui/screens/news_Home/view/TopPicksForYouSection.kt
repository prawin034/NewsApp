package com.skyapp.newsapp.ui.screens.news_Home.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skyapp.newsapp.domain.model.Article
import com.skyapp.newsapp.ui.common.AppAsyncImg
import com.skyapp.newsapp.ui.common.AppCard
import com.skyapp.newsapp.ui.common.AppCardHeader
import com.skyapp.newsapp.ui.common.AppCardLabel
import com.skyapp.newsapp.ui.common.AppCmnRow
import com.skyapp.newsapp.ui.common.AppSectionTextHeader
import com.skyapp.newsapp.ui.common.AppTextBody1
import com.skyapp.newsapp.ui.common.AppTextBody2
import com.skyapp.newsapp.ui.common.AppTxtShowMore
import com.skyapp.newsapp.ui.screens.news_Article.viewmodel.NewsFeedUiState
import com.skyapp.newsapp.ui.utils.parseValidDateString
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsHomeTopPickupForYouSection(getAllArticles: NewsFeedUiState)
{
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(paddingValues = PaddingValues(
        start = 20.dp,
        end = 0.dp,
        top = 24.dp,
        bottom = 0.dp
    )),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    )
    {
        AppCmnRow(

        ) {
            AppSectionTextHeader(text = "Top Picks For You")
            AppTxtShowMore("Show More")
            {

            }
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

//        when {
//            getAllArticles.isLoading -> {
//                CircularProgressIndicator()
//            }
//            getAllArticles.error != null -> {
//                Toast.makeText(context, getAllArticles.error,Toast.LENGTH_SHORT).show()
//                Log.d("WhatEror","${getAllArticles.error}")
//            }
//            else -> {
//
//            }
//        }

        LoadTopPickupForYouSection(getAllArticles.article)






    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LoadTopPickupForYouSection(article: List<Article>) {


    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(article) { index, item ->
            val formattedDate = remember(item.publishedAt) {
                parseValidDateString(item.publishedAt)
            }
            AppCard(
                modifier = Modifier
                    .fillParentMaxWidth(0.8f)
                    .height(430.dp)
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(18.dp)
                    ),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                //image
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.TopEnd
                    ){
                        AppAsyncImg(
                            imageUrl = item.imageUrl,
                            modifier = Modifier
                                .padding(top = 3.dp)
                                .fillMaxWidth()
                                .height(300.dp)
                                .background(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(
                                        topStart = 23.dp,
                                        topEnd = 23.dp,
                                        bottomStart = 23.dp,
                                        bottomEnd = 23.dp
                                    )
                                )
                                .clip(
                                    RoundedCornerShape(
                                        topStart = 23.dp,
                                        topEnd = 23.dp,
                                        bottomStart = 23.dp,
                                        bottomEnd = 23.dp
                                    )
                                ),
                            onClick = {}
                        )



                        AppCardLabel(
                            text = item.newsSite,
                            icon = true,
                            appendIconFront = true,
                            fontSize = 8.sp,
                            modifier = Modifier.padding(5.dp).padding(horizontal = 3.dp)

                        ) {}
                    }



                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(12.dp)
                    ) {

                        //Trending no 1
                        AppCmnRow {
                            AppTextBody1(
                                "Trending no ${index+1}🔥",
                                color = Color.LightGray,
                                fontSize = 11.sp,
                            )



                            AppTextBody1(
                                formattedDate,
                                color = Color.LightGray,
                                fontSize = 10.sp,
                            )

                        }


                        BottomTxtContent(
                            title = item.title,
                            summary = item.summary,
                            imageUrl = item.imageUrl
                        )



                    }



                }


                //content
            }
        }
    }




}


@Composable
fun BottomTxtContent(
    title: String,
    summary: String,
    imageUrl : String,
) {
    Column(
        modifier = Modifier
    ) {
        AppCmnRow(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {

            AppAsyncImg(
                imageUrl = imageUrl,
                modifier = Modifier.size(30.dp).clip(CircleShape),
                onClick = {}
            )

            AppCardHeader(
                title = title,
                maxLines = 1,
                fontSize = 8.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 10.dp),

                )
        }


        Spacer(modifier = Modifier.height(0.dp))

        AppTextBody2(
            title = summary.capitalize(Locale.ROOT),
            maxLines = 3,
            color = Color.Black,
            fontSize = 10.sp,
        )
    }
}