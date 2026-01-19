package com.skyapp.newsapp.ui.screens.news_Detail.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skyapp.newsapp.data.datastore.UserPreferences
import com.skyapp.newsapp.data.datastore.UserPrefsManager
import com.skyapp.newsapp.domain.model.Article
import com.skyapp.newsapp.domain.repository.NewsDetailRepository
import com.skyapp.newsapp.domain.repository.NewsFeedRepository
import com.skyapp.newsapp.ui.screens.news_Article.viewmodel.NewsFeedUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailedViewModel   @Inject  constructor(
    private val repository: NewsDetailRepository,
    private val repositoryNewsFeed: NewsFeedRepository,
    private val userPrefsManager: UserPrefsManager
) : ViewModel() {


    init {
        Log.d("TAG", "NewsDetailedViewModel init: ")
    }



    val userPrefsData = userPrefsManager.userPrefsFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = UserPreferences.getDefaultInstance()
    )

    //getAll news
    private val _newsUiState = MutableStateFlow(NewsFeedUiState())
    val newsUiState  : StateFlow<NewsFeedUiState> get() = _newsUiState


    private val limit = 20
    private var offset = 0
    private var endReached = false

    fun getAllNewsArticles(offset: Int = this.offset) {
        if (_newsUiState.value.isLoading || endReached) return

        viewModelScope.launch {
            _newsUiState.update { it.copy(isLoading = true, error = null) }
            try {
                val result = repositoryNewsFeed.getAllNewsArticles(limit, offset)
                _newsUiState.update {
                    it.copy(
                        isLoading = false,
                        article = it.article + result
                    )
                }
                if (result.size < limit) endReached = true
                else this@NewsDetailedViewModel.offset += limit
            } catch (e: Exception) {
                _newsUiState.update {
                    it.copy(isLoading = false, error = e.message ?: "Something went wrong")
                }
            }
        }
    }

    fun resetPagination() {
        offset = 0
        endReached = false
        _newsUiState.value = NewsFeedUiState()
    }


    override fun onCleared() {
        super.onCleared()
        resetPagination()
    }

}