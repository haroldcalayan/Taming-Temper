package com.haroldcalayan.tamingtemper.data.source.remote.api

import android.content.Context
import com.google.gson.Gson
import com.haroldcalayan.tamingtemper.BuildConfig
import com.haroldcalayan.tamingtemper.data.source.remote.model.TamingActivityResponse

class TamingApi(private val context: Context) {
    fun getTamingApi(): TamingActivityResponse {
        val jsonString = context.assets.open(BuildConfig.RESPONSE_JSON_FILE).bufferedReader().use {it.readText()}
        return Gson().fromJson(jsonString, TamingActivityResponse::class.java)
    }
}