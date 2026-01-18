package com.skyapp.newsapp.ui.screens.news_Home.view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skyapp.newsapp.domain.model.Article
import com.skyapp.newsapp.ui.common.AppAsyncImg
import com.skyapp.newsapp.ui.common.AppBtn
import com.skyapp.newsapp.ui.common.AppCard
import com.skyapp.newsapp.ui.common.AppCardHeader
import com.skyapp.newsapp.ui.common.AppCmnRow
import com.skyapp.newsapp.ui.common.AppTextBody1
import com.skyapp.newsapp.ui.common.MoreBtn
import com.skyapp.newsapp.ui.screens.news_Article.viewmodel.NewsFeedUiState

import java.util.Locale



@Composable
fun NewsFeed(
    item: Article,
    onClickDetail: (Int) -> Unit = {}
) {


   // val formattedData by remember(item.publishedAt) { parseValidDateString(item.publishedAt) }
//    val formattedDate = remember(item.publishedAt) {
//        parseValidDateString(item.publishedAt)
//    }
    AppCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .padding(24.dp)
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(18.dp)
            )
            .clickable {
                onClickDetail.invoke(item.id)
            }

        ,
        shape = RoundedCornerShape(18.dp),
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {

            // LEFT - 70% content
            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .fillMaxHeight()
            ) {

                AppCmnRow {
                    AppTextBody1(
                        item.newsSite,
                        color = Color.LightGray,
                        fontSize = 10.sp,
                    )
                    AppTextBody1(
                        item.publishedAt,
                        color = Color.LightGray,
                        fontSize = 10.sp,
                    )

                }

                Spacer(modifier = Modifier.height(8.dp))


                AppCardHeader(
                    title = item.title.capitalize(Locale.ROOT),
                    maxLines = 2,
                    fontSize = 10.9.sp,
                    fontFamily = FontFamily.Monospace
                )

                AppCmnRow {

                    AppBtn(
                        icon = Icons.Default.BookmarkBorder,
                        color = Color.LightGray
                    ) {

                    }


                    MoreBtn(
                        icon = Icons.Default.MoreHoriz,
                        color = Color.LightGray
                    ) { }

                }

            }

            Spacer(modifier = Modifier.width(12.dp))

            // RIGHT - 30% image
            AppAsyncImg(
                imageUrl = item.imageUrl,
                modifier = Modifier.weight(0.3f).fillMaxHeight(0.8f).clip(RoundedCornerShape(14.dp)),
                onClick = {}
            )
        }
    }










}