package com.skyapp.newsapp.ui.screens.news_Home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skyapp.newsapp.domain.repository.NewsFeedRepository
import com.skyapp.newsapp.ui.screens.news_Article.viewmodel.NewsFeedUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsHomeScreenViewModel  @Inject constructor(
    private val repository: NewsFeedRepository
): ViewModel() {

    private val _newsUiState = MutableStateFlow(NewsFeedUiState())
    val newsUiState  : StateFlow<NewsFeedUiState> get() = _newsUiState




    fun getAllNewsArticles(
        limit: Int = 20, offset: Int = 0
    ) {
        viewModelScope.launch {
            _newsUiState.value = NewsFeedUiState(
                isLoading = true,
                error = null
            )
            try {
                val result =  repository.getAllNewsArticles(limit,offset)

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