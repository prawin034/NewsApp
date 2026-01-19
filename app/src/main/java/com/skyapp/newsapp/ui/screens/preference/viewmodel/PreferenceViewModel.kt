package com.skyapp.newsapp.ui.screens.preference.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skyapp.newsapp.data.datastore.UserPreferences
import com.skyapp.newsapp.data.datastore.UserPrefsManager
import com.skyapp.newsapp.domain.repository.PreferencesRepository
import com.skyapp.newsapp.ui.screens.news_Article.viewmodel.NewsFeedUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class PreferenceViewModel  @Inject  constructor(
    private val preferencesRepository: PreferencesRepository,
    private val userPrefsManager: UserPrefsManager
): ViewModel()
{



    private val _prefsUiState = MutableStateFlow(NewsFeedUiState())
    val prefsUiState = _prefsUiState




    private val _myPrefs = MutableStateFlow<List<String>>(listOf())
    val myPrefs = _myPrefs



    val userPrefsData = userPrefsManager.userPrefsFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = UserPreferences.getDefaultInstance()
    )



    fun togglePrefsDataStore(prefsValue: String) {
        viewModelScope.launch {
            userPrefsManager.togglePreference(item = prefsValue)
        }
    }

    fun setPreference(boolean: Boolean){
        viewModelScope.launch{
            userPrefsManager.setPreference(boolean)
        }
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