package com.skyapp.newsapp.ui.screens.news_Home.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdsClick
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
import androidx.navigation.NavController
import com.skyapp.newsapp.data.datastore.UserPreferences
import com.skyapp.newsapp.domain.model.Article
import com.skyapp.newsapp.ui.common.AppAsyncImg
import com.skyapp.newsapp.ui.common.AppBtn
import com.skyapp.newsapp.ui.common.AppCard
import com.skyapp.newsapp.ui.common.AppCardHeader
import com.skyapp.newsapp.ui.common.AppCardLabel
import com.skyapp.newsapp.ui.common.AppCmnRow
import com.skyapp.newsapp.ui.common.AppSectionTextHeader
import com.skyapp.newsapp.ui.common.AppTextBody1
import com.skyapp.newsapp.ui.common.AppTextBody2
import com.skyapp.newsapp.ui.common.AppTxtShowMore
import com.skyapp.newsapp.ui.common.shimmerEffect
import com.skyapp.newsapp.ui.navigation.NewsScreens
import com.skyapp.newsapp.ui.screens.news_Article.viewmodel.NewsFeedUiState
import com.skyapp.newsapp.ui.utils.parseValidDateString
import java.util.Locale

@Composable
fun NewsHomeTopPickupForYouSection(
    isDarkModeEnabled: Boolean= false,
    getAllArticles: NewsFeedUiState,
    navController: NavController
)
{
    val context = LocalContext.current
    //if(myPrefsData.isDarkModeEnabled) Color.White else Color(0xFF000000)

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
            AppSectionTextHeader(text = "Top Picks For You", textColor = if(isDarkModeEnabled) Color.White else Color(0xFF000000))
            AppTxtShowMore("Show More")
            {
                navController.navigate(NewsScreens.NewsArticleScreen.route)
            }
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        when {
            getAllArticles.isLoading -> {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(20) {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                                .fillParentMaxWidth(0.8f)
                                .height(430.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(18.dp)
                                ).shimmerEffect(shape = RoundedCornerShape(18.dp)),
                        )
                    }
                }
            }
            getAllArticles.article.isNotEmpty() -> {
                LoadTopPickupForYouSection(getAllArticles.article) {
                    navController.navigate(NewsScreens.NewsDetailScreen.passArgs(it))
                }
            }
        }








    }
}


@Composable
fun LoadTopPickupForYouSection(
    article: List<Article>,
    onClick: (Int) -> Unit = {}
) {


    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(article) { index, item ->
//            val formattedDate = remember(item.publishedAt) {
//                parseValidDateString(item.publishedAt)
//            }
            AppCard(
                modifier = Modifier
                    .clickable {
                        onClick.invoke(item.id)
                    }
                    .fillParentMaxWidth(0.8f)
                    .height(430.dp)

                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(18.dp)
                    ),
                shape = RoundedCornerShape(18.dp),

            ) {
                //image
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .clickable{
                                onClick.invoke(item.id)
                            }.weight(1f),
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



                            if(item.publishedAt.isNotEmpty()) {
                                AppTextBody1(
                                    item.publishedAt.take(10),
                                    color = Color.LightGray,
                                    fontSize = 10.sp,
                                )
                            }


                        }


                        BottomTxtContent(
                            title = item.title,
                            summary = item.summary,
                            imageUrl = item.imageUrl,
                            id = item.id,
                            onClick = {
                                onClick.invoke(it)
                            }
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
    id: Int,
    title: String,
    summary: String,
    imageUrl : String,
    onClick: (Int) -> Unit
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

        AppBtn(
            icon = Icons.Default.AdsClick,
            color = Color.Black
        ) {
            onClick.invoke(id)
        }
    }
}