package com.skyapp.newsapp.data.datastore

import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream

object UserPrefsSerializer : Serializer<UserPreferences> {
    override val defaultValue: UserPreferences
        get() = UserPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPreferences {
        try {
           return UserPreferences.parseFrom(input)
        }catch (e: Exception) {
            throw Exception("Cannot read proto.", e)
        }
    }

    override suspend fun writeTo(
        t: UserPreferences,
        output: OutputStream,
    )  = t.writeTo(output)

}