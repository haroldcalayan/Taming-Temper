package com.haroldcalayan.tamingtemper.data.source.remote.api

import android.content.Context
import com.google.gson.Gson
import com.haroldcalayan.tamingtemper.data.source.remote.model.TamingActivityResponse
import kotlinx.coroutines.delay

class TamingApi(private val context: Context) {
    suspend fun getTamingApi(): TamingActivityResponse {
        val jsonString = context.assets.open("response_1709543815894.json").bufferedReader().use {it.readText()}
        return Gson().fromJson(jsonString, TamingActivityResponse::class.java)
    }
}