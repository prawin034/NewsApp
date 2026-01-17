package com.skyapp.newsapp.ui.screens.news_Article.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skyapp.newsapp.domain.model.Article
import com.skyapp.newsapp.domain.repository.NewsFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsArticleViewModel  @Inject constructor(
    private val repository: NewsFeedRepository
)    : ViewModel() {


    private val _newsUiState = MutableStateFlow(NewsFeedUiState())
    val newsUiState  : StateFlow<NewsFeedUiState> get() = _newsUiState




    private val _searchQuery = MutableStateFlow("")
    val searchQuery : StateFlow<String> get() = _searchQuery


    fun onChangeSearchQuery(value : String) {
        _searchQuery.value = value
    }

    fun clearSearch(){
        _searchQuery.value = ""
    }

    private val _searchList = MutableStateFlow<List<String>>(listOf())
    val searchList : StateFlow<List<String>> get() = _searchList

    fun addSearchList(value: String) {
        _searchList.value = _searchList.value.plus(value)
    }
    fun removeSearchList(value: String){
        _searchList.value = _searchList.value.minus(value)
    }



    fun getAllNewsArticles(
        limit : Int = 20,
        offset: Int = 0,
        search: String? = null
    ) {
        viewModelScope.launch {

            _newsUiState.update {
                it.copy(isLoading = true, error = null)
            }

            try {
                val result =  repository.getAllNewsArticles(limit,offset,search)

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
    val error : String? = null,
)