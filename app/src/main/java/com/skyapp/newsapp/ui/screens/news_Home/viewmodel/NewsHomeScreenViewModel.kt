package com.skyapp.newsapp.ui.screens.news_Home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skyapp.newsapp.domain.repository.NewsFeedRepository
import com.skyapp.newsapp.ui.screens.news_Article.viewmodel.NewsFeedUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsHomeScreenViewModel  @Inject constructor(
    private val repository: NewsFeedRepository
): ViewModel() {

    private val _newsUiState = MutableStateFlow(NewsFeedUiState())
    val newsUiState  : StateFlow<NewsFeedUiState> get() = _newsUiState







    fun getAllNewsArticles(
        limit: Int = 20, offset: Int = 0,
    ) {
        viewModelScope.launch {

            _newsUiState.update {
                it.copy(
                    isLoading = true
                )
            }
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




    private val limit = 20
    private var endReached = false
    private var offSet: Int = 0
    private val _getNewsArticlesPaginated = MutableStateFlow(NewsFeedUiState())
    val getNewsArticlesPaginated  : StateFlow<NewsFeedUiState> get() = _getNewsArticlesPaginated

    fun getNewsArticlesPaginated(){
        if(_getNewsArticlesPaginated.value.isLoading) return

        viewModelScope.launch {
            if(endReached) return@launch

            _getNewsArticlesPaginated.update {
                it.copy(
                    isLoading = true
                )
            }


            try {
                val result = repository.getAllNewsArticles(
                    limit = limit,
                    offset = offSet
                )


                _getNewsArticlesPaginated.update {
                    it.copy(
                        isLoading = false,
                        article = it.article.plus(result)
                    )
                }
                if(result.size <limit) endReached = true
                else offSet+= limit

            }catch (e: Exception) {
                _getNewsArticlesPaginated.value = NewsFeedUiState(
                    isLoading = false,
                    error = e.message ?: "Something went wrong"
                )
            }










        }


    }
}