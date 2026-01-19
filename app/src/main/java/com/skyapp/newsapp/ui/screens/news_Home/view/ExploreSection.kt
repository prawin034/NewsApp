package com.skyapp.newsapp.ui.screens.news_Home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.skyapp.newsapp.ui.common.AppAsyncImg
import com.skyapp.newsapp.ui.common.AppCmnRow
import com.skyapp.newsapp.ui.common.AppSectionTextHeader
import com.skyapp.newsapp.ui.common.AppTextBody2
import com.skyapp.newsapp.ui.common.AppTxtShowMore
import com.skyapp.newsapp.ui.common.shimmerEffect
import com.skyapp.newsapp.ui.screens.news_Article.viewmodel.NewsFeedUiState
import java.util.Locale

@Composable
fun ExploreNewsSection(

    getAllArticles: NewsFeedUiState,
    isDarkModeEnabled: Boolean= false,
    exploreMore :() -> Unit = {}
) {



    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(paddingValues = PaddingValues(
            start = 20.dp,
            end = 0.dp,
            top = 24.dp,
            bottom = 0.dp
        )),
        horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top
    ){


        //1- header
        AppCmnRow(

        ) {
            AppSectionTextHeader(text = "Explore Now", textColor = if(isDarkModeEnabled) Color.White else Color(0xFF000000))
            AppTxtShowMore("See more".capitalize(Locale.ROOT)) {
                exploreMore.invoke()
            }
        }

        Spacer(modifier = Modifier.height(20.dp))


        //2- Explore grid list

        when {
            getAllArticles.isLoading -> {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(20) {
                        Box(
                            modifier = Modifier.size(100.dp).clip(CircleShape).shimmerEffect(shape = CircleShape),
                        )
                    }
                }
            }
            getAllArticles.article.isNotEmpty() -> {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    itemsIndexed(getAllArticles.article) {index, item ->
                        Box(
                            modifier = Modifier.size(100.dp).clip(CircleShape),
                            contentAlignment = Alignment.Center
                        ){
                            AsyncImage(
                                model = item.imageUrl,
                                contentDescription = "",
                                modifier = Modifier.clip(CircleShape).aspectRatio(1f),
                                contentScale = ContentScale.Crop
                            )

                            Box(modifier = Modifier.matchParentSize().background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.7f)
                                    )
                                ),
                                shape = CircleShape
                            ))


                            AppTextBody2(
                                title = item.newsSite,
                                color = Color.White,
                                fontSize = 5.4.sp,
                                maxLines = 2

                            )
                        }



                    }
                }
            }

        }








    }



}