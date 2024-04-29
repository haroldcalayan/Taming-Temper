package com.haroldcalayan.tamingtemper.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.haroldcalayan.tamingtemper.common.JsonUtil
import com.haroldcalayan.tamingtemper.common.constant.PreferenceKey
import com.haroldcalayan.tamingtemper.data.source.remote.model.TamingActivityResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TamingStorageRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val jsonUtil: JsonUtil
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(PreferenceKey.PREF_STORAGE_KEY)

    suspend fun saveTamingActivityValue(configurationValue: TamingActivityResponse) {
        withContext(Dispatchers.IO) {
            context.dataStore.edit { pref ->
                pref[stringPreferencesKey(PreferenceKey.TAMING_ACTIVITY)] =
                    jsonUtil.encodeToString(configurationValue)
            }
        }
    }

    suspend fun getTamingActivityValue(): TamingActivityResponse? {
        return withContext(Dispatchers.IO) {
            context.dataStore.data.map { preferences ->
                preferences[stringPreferencesKey(PreferenceKey.TAMING_ACTIVITY)]?.let {
                    jsonUtil.decodeFromString<TamingActivityResponse>(
                        it
                    )
                }
            }.firstOrNull()
        }
    }

}
