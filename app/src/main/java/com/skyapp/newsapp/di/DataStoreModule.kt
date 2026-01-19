package com.skyapp.newsapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.skyapp.newsapp.data.datastore.UserPreferences
import com.skyapp.newsapp.data.datastore.UserPrefsManager
import com.skyapp.newsapp.data.datastore.UserPrefsSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {



    @Provides
    @Singleton
     fun provideUserPrefsDataStore(@ApplicationContext context: Context) : DataStore<UserPreferences> {

      return  DataStoreFactory.create(
            serializer = UserPrefsSerializer,
            produceFile = {  File(context.filesDir, "user_prefs.pb")}
        )
     }


    @Provides
    @Singleton
    fun provideUserPrefsManager(dataStore: DataStore<UserPreferences>) : UserPrefsManager {
        return UserPrefsManager(dataStore)
    }

}