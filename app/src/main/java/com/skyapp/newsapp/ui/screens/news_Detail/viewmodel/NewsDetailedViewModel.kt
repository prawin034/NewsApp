package com.skyapp.newsapp.ui.screens.news_Detail.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skyapp.newsapp.domain.model.Article
import com.skyapp.newsapp.domain.repository.NewsDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailedViewModel   @Inject  constructor(
    private val repository: NewsDetailRepository
) : ViewModel() {


    init {
        Log.d("TAG", "NewsDetailedViewModel init: ")
    }

    private val _getNewsDetails = MutableStateFlow<Article?>(null)
    val getNewsDetails : StateFlow<Article?> get() = _getNewsDetails



    private val _successMsg = MutableStateFlow("")
    val successMsg get() = _successMsg

    private val _failureMsg = MutableStateFlow("")
    val failureMsg get() = _failureMsg

    private val _isLoading = MutableStateFlow(false)
    val isLoading get() = _isLoading


    fun getNewsDetail(id: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = repository.getNewsArticleById(id)
            when {
                result.isSuccess -> {
                    val responseData = result.getOrNull()
                    if(responseData !=null) {
                         _isLoading.value = false
                        _getNewsDetails.value = responseData
                        _successMsg.value = "Successfully loaded!"
                    }
                }
                result.isFailure -> {
                    _isLoading.value = false
                    val exception = result.exceptionOrNull()
                    _failureMsg.value = exception?.message ?: "Something went wrong"
                }
            }

        }
    }









}