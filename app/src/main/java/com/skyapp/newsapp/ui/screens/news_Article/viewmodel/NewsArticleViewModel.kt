package com.skyapp.newsapp.ui.screens.news_Article.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skyapp.newsapp.domain.model.Article
import com.skyapp.newsapp.domain.repository.NewsFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsArticleViewModel  @Inject constructor(
    private val repository: NewsFeedRepository
)    : ViewModel() {


    private val _newsUiState = MutableStateFlow(NewsFeedUiState())
    val newsUiState  : StateFlow<NewsFeedUiState> get() = _newsUiState





    fun getAllNewsArticles() {
        viewModelScope.launch {
            _newsUiState.value = NewsFeedUiState(
                isLoading = true,
                error = null
            )
            try {
                val result =  repository.getAllNewsArticles()

                _newsUiState.value = NewsFeedUiState(
                    isLoading = false,
                    article = result,

                )
            }catch (e: Exception) {
                _newsUiState.value = NewsFeedUiState(
                    isLoading = false,
                    error = e.message ?: "Something went wrong"
                )
            }
        }
    }

}


data class NewsFeedUiState(
     val isLoading : Boolean = false,
     val article : List<Article> = emptyList(),
    val error : String? = null
)