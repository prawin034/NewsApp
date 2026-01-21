package com.skyapp.newsapp.ui.screens.news_Article.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skyapp.newsapp.data.datastore.UserPreferences
import com.skyapp.newsapp.data.datastore.UserPrefsManager
import com.skyapp.newsapp.domain.model.Article
import com.skyapp.newsapp.domain.repository.NewsFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsArticleViewModel  @Inject constructor(
    private val repository: NewsFeedRepository,
    private val userPrefsManager: UserPrefsManager
)    : ViewModel() {


    private val _newsUiState = MutableStateFlow(NewsFeedUiState())
    val newsUiState  : StateFlow<NewsFeedUiState> get() = _newsUiState




    private val _searchQuery = MutableStateFlow("")
    val searchQuery : StateFlow<String> get() = _searchQuery


    fun onChangeSearchQuery(value : String) {
        _searchQuery.value = value
    }


    private val _isPullToRefreshTriggered = MutableStateFlow(false)
    val isPullToRefreshTriggered = _isPullToRefreshTriggered


    fun pullToRefresh(boolean: Boolean) {
        _isPullToRefreshTriggered.value = boolean
    }



    private val _searchList = MutableStateFlow<List<String>>(listOf())
    val searchList : StateFlow<List<String>> get() = _searchList

    fun addSearchList(value: String) {
        _searchList.value = _searchList.value.plus(value)
    }
    fun removeSearchList(value: String){
        _searchList.value = _searchList.value.minus(value)
    }

    fun clearSearchQuery() {
        _searchQuery.value = ""
    }
    val userPrefsData = userPrefsManager.userPrefsFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = UserPreferences.getDefaultInstance()
    )

    fun getAllNewsArticles(
        limit : Int = 20,
        offset: Int = 0,
        search: String? = null
    ) {
        viewModelScope.launch {

//            _newsUiState.update {
//                it.copy(isLoading = true, error = null)
//            }
            _getNewsArticlesPaginated.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            try {
                val result =  repository.getAllNewsArticles(limit,offset,search)

//                _newsUiState.value = NewsFeedUiState(
//                    isLoading = false,
//                    article = result,
//
//                )
                _getNewsArticlesPaginated.value  = NewsFeedUiState(
                    isLoading = false,
                    article = result
                )
                resetPagination()
            }catch (e: Exception) {
                _getNewsArticlesPaginated.value = NewsFeedUiState(
                    isLoading = false,
                    error = e.message ?: "Something went wrong"
                )
            }
        }
    }





    private val limit = 20
    private var endReached = false
     var offSet: Int = 0
    private val _getNewsArticlesPaginated = MutableStateFlow(NewsFeedUiState())
    val getNewsArticlesPaginated  : StateFlow<NewsFeedUiState> get() = _getNewsArticlesPaginated



    fun resetPagination() {
        offSet = 0
        endReached = false
    }


    fun getNewsArticlesPaginated(

    ){
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
                    offset = offSet,
                    search = searchQuery.value
                )


                _getNewsArticlesPaginated.update { state ->
                    val existingIds = state.article.map { it.id }.toSet()

                    val newArticles = result.filter { it.id !in existingIds }

                    state.copy(
                        isLoading = false,
                        article = state.article + newArticles
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


data class NewsFeedUiState(
     val isLoading : Boolean = false,
     val article : List<Article> = emptyList(),
    val error : String? = null,
)