package com.skyapp.newsapp.ui.screens.news_Home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skyapp.newsapp.data.datastore.UserPreferences
import com.skyapp.newsapp.data.datastore.UserPrefsManager
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
class NewsHomeScreenViewModel  @Inject constructor(
    private val repository: NewsFeedRepository,
    private val userPrefsManager: UserPrefsManager
): ViewModel() {

    private val _newsUiState = MutableStateFlow(NewsFeedUiState())
    val newsUiState  : StateFlow<NewsFeedUiState> get() = _newsUiState



    val userPrefsData = userPrefsManager.userPrefsFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = UserPreferences.getDefaultInstance()
    )


    fun toggleDarkMode() {
        viewModelScope.launch {
            userPrefsManager.toggleDarkModeEnabled()
        }
    }





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


                Log.d("offset","$offset")
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
    public var offSet =MutableStateFlow<Int>(0)
    private val _getNewsArticlesPaginated = MutableStateFlow(NewsFeedUiState())
    val getNewsArticlesPaginated  : StateFlow<NewsFeedUiState> get() = _getNewsArticlesPaginated



    private val _isPullToRefreshTriggered = MutableStateFlow(false)
    val isPullToRefreshTriggered = _isPullToRefreshTriggered


    fun pullToRefresh(boolean: Boolean) {
        _isPullToRefreshTriggered.value = boolean
    }

    fun resetPagination() {
        offSet.value = 0
        endReached = false
    }


    fun updateOffEachTimeAtEnd() {
        offSet.value += limit
        Log.d("offsetupdated","updated")
    }

    fun getNewsArticlesPaginated(

    ){
        if(_getNewsArticlesPaginated.value.isLoading) return

        Log.d("NewsArticalePaginated","called twice")
        viewModelScope.launch {
            if(endReached) return@launch

            _getNewsArticlesPaginated.update {
                it.copy(
                    isLoading = true
                )
            }


            Log.d("offset","${offSet.value}")
            try {
                val result = repository.getAllNewsArticles(
                    limit = limit,
                    offset = offSet.value
                )



                _getNewsArticlesPaginated.update {
                    it.copy(
                        isLoading = false,
                        article = it.article.plus(result)
                    )
                }
                if(result.size <limit) endReached = true
               // else offSet.value+= limit
                Log.d("offsetAfter","${offSet.value}")

            }catch (e: Exception) {
                _getNewsArticlesPaginated.value = NewsFeedUiState(
                    isLoading = false,
                    error = e.message ?: "Something went wrong"
                )
            }
        }


    }
}