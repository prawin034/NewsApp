package com.skyapp.newsapp.ui.screens.bookmark.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skyapp.newsapp.data.datastore.UserPreferences
import com.skyapp.newsapp.data.datastore.UserPrefsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject  constructor(
    private val userPrefsManager: UserPrefsManager
)  : ViewModel() {



    init {
        Log.d("BookMarkViewModel","BookMarkViewModel init")
    }


    val userPrefsData = userPrefsManager.userPrefsFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = UserPreferences.getDefaultInstance()
    )







}