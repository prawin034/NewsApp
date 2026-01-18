package com.skyapp.newsapp.ui.screens.preference.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skyapp.newsapp.domain.repository.PreferencesRepository
import com.skyapp.newsapp.ui.screens.news_Article.viewmodel.NewsFeedUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class PreferenceViewModel  @Inject  constructor(
    private val preferencesRepository: PreferencesRepository
): ViewModel()
{



    private val _prefsUiState = MutableStateFlow(NewsFeedUiState())
    val prefsUiState = _prefsUiState




    private val _myPrefs = MutableStateFlow<List<String>>(listOf())
    val myPrefs = _myPrefs


    fun addMyPrefs(prefsname : String) {
        _myPrefs.value = _myPrefs.value.plus(prefsname)
    }

    fun removeMyPrefs(prefs: String) {
        _myPrefs.value = _myPrefs.value.minus(prefs)
    }




    fun getNewsPrefs(
        limit : Int,offset: Int,search: String? = null
    ) {
        viewModelScope.launch {
            try {
                _prefsUiState.update {
                    it.copy(
                        isLoading = true,
                        error = null
                    )
                }


                val result = preferencesRepository.getAllNewsPrefs(limit,offset,search)


                _prefsUiState.update {
                    it.copy(
                        isLoading = false,
                        article = result
                    )
                }

            }catch (e: HttpException) {
                _prefsUiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Server error: ${e.code()}"
                    )
                }
            } catch (e: IOException) {
                _prefsUiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Network error"
                    )
                }
            } catch (e: Exception) {
                _prefsUiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Something went wrong"
                    )
                }
            }
        }
    }







}