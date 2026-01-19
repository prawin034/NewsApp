package com.skyapp.newsapp.ui.screens.news_Detail.view

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.size.Size
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.skyapp.newsapp.domain.model.Article
import com.skyapp.newsapp.ui.common.AppCardHeader
import com.skyapp.newsapp.ui.common.AppCmnRow
import com.skyapp.newsapp.ui.common.AppScaffold
import com.skyapp.newsapp.ui.common.AppTextBody1
import com.skyapp.newsapp.ui.common.AppTextBody2
import com.skyapp.newsapp.ui.common.AppTopAppBar
import com.skyapp.newsapp.ui.common.BackIconButton
import com.skyapp.newsapp.ui.screens.news_Detail.viewmodel.NewsDetailedViewModel
import com.skyapp.newsapp.ui.utils.NewsAppConstants
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailedScreen(
    navController: NavController,
    articleId: Int,
    newsDetailedViewModel: NewsDetailedViewModel = hiltViewModel()
) {


    val context = LocalContext.current
    val myPrefsData by newsDetailedViewModel.userPrefsData.collectAsStateWithLifecycle()


    val systemUiController = rememberSystemUiController()
    val isDarkMode = myPrefsData.isDarkModeEnabled

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (isDarkMode) Color.Black else Color(NewsAppConstants.bgColor),
            darkIcons = !isDarkMode // ✅ THIS IS THE KEY
        )
    }


    val getAllNews by newsDetailedViewModel.newsUiState.collectAsStateWithLifecycle()

    val pager = rememberPagerState(
        pageCount =  { getAllNews.article.size }
    )


    var hasScrolled = rememberSaveable { mutableStateOf(false) }

    // Scroll to the clicked article
    LaunchedEffect(getAllNews.article, articleId) {
        if (!hasScrolled.value) {
            val index = getAllNews.article.indexOfFirst { it.id == articleId }
            if (index >= 0) {
                pager.scrollToPage(index)
                hasScrolled.value = true
            } else {
                // Article not loaded yet → load next page
                newsDetailedViewModel.getAllNewsArticles()
            }
        }
    }

    // Fetch initial articles if empty
    LaunchedEffect(Unit) {
        if (getAllNews.article.isEmpty()) {
            newsDetailedViewModel.getAllNewsArticles()
        }
    }
    AppScaffold(
        isDarkModeEnabled = isDarkMode,
        topAppBar = {
            AppTopAppBar(
                isDarkModeEnabled = isDarkMode,
                navigationIcon = {
                    BackIconButton(
                    ) {
                        navController.popBackStack()
                    }
                },
                title = {},
                actions = {
                    Row {
                    }
                }
            )
        },
        bottomAppBar = {},
        floatingBtn = {},
        snackBarHost = {},
        content = {
             HorizontalPager(
                 state = pager,
                 modifier = Modifier.fillMaxSize().padding(it)
             ) { pager ->

                 NewsDetailContent(
                     articleDetails = getAllNews.article[pager],
                     isDarkModeEnabled = isDarkMode
                 )

             }

        }
    )


}

@Composable
fun NewsDetailContent(
    articleDetails : Article?,
    isDarkModeEnabled : Boolean =false,
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 0.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    )
    {
        //1. Detailed image--
        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f)) {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                AsyncImage(
                    model = articleDetails?.imageUrl ?:"",
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                )
                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.7f)
                                )
                            ),
                            shape = RoundedCornerShape(0.dp)
                        )
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.Start
                ) {

                    // Title
                    AppCardHeader(
                        title = articleDetails?.newsSite.plus("."),
                        maxLines = 2,
                        fontSize = 10.sp,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth()
                    )
                    AppTextBody1(
                        title = articleDetails?.title ?:"",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        fontSize = 16.5.sp
                    )



                    OutlinedButton(
                        onClick = {

                            articleDetails?.url?.takeIf { it.isNotEmpty() }?.let { url ->
                                try {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                    context.startActivity(intent)
                                }catch (e: Exception) {
                                    Toast.makeText(context,"Something went wrong ${e.message}",
                                        Toast.LENGTH_SHORT).show()
                                    Log.d("TAG", "NewsDetailedScreen: ${e.message}")
                                }
                            }


                        },
                        border = BorderStroke(1.dp, Color.White),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .width(150.dp)
                            .height(55.dp)
                    ) {
                        AppCmnRow {
                            Text("Read more ")
                            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "", tint = Color.White)
                        }
                    }

                }





            }

        }

        //2 . Detailed Description
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp).verticalScroll(
                rememberScrollState()
            ),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            AppCmnRow {

                Column {
                    AppCardHeader(
                        title = articleDetails?.newsSite ?:"",
                        color = if(isDarkModeEnabled) Color.White else Color(0xFF000000)
                    )

                    AppTextBody2(
                        title = articleDetails?.publishedAt ?:"",
                        color =   if(isDarkModeEnabled) Color.White else Color(0xFF000000)
                    )
                }



                AsyncImage(
                    model = articleDetails?.imageUrl ?:"",
                    contentDescription = null,
                    modifier = Modifier.size(55.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop,

                    )





            }


            Spacer(modifier = Modifier.height(10.dp))
            repeat(10) {
                AppTextBody1(
                    title =  articleDetails?.summary?.capitalize(Locale.ROOT) ?:"",
                    fontSize = 13.5.sp,
                    modifier = Modifier.padding(4.dp),
                    fontFamily = FontFamily.SansSerif,
                    color = if(isDarkModeEnabled) Color.White else Color(0xFF000000)
                )
            }



        }


    }
}