package com.skyapp.newsapp.data.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPrefsManager @Inject  constructor(
    private val dataStore : DataStore<UserPreferences>
) {


    val userPrefsFlow: Flow<UserPreferences> = dataStore.data


    suspend fun togglePreference(item: String) {
        dataStore.updateData {

            val newList =
                if (it.preferencesList.contains(item))
                    it.preferencesList - item
                else it.preferencesList + item


            it.toBuilder()
                .clearPreferences()
                .addAllPreferences(newList)
                .build()
        }
    }


    suspend fun toggleDarkModeEnabled() {
        dataStore.updateData { prefs ->
            prefs.toBuilder()
                .setIsDarkModeEnabled(!prefs.isDarkModeEnabled)
                .build()
        }
    }

    suspend fun setPreference(value: Boolean = false) {
        dataStore.updateData {
            it.toBuilder()
                .setIsPreferenceSet(value)
                .build()
        }
    }
}