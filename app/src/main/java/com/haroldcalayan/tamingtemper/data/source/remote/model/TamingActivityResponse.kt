package com.haroldcalayan.tamingtemper.data.source.remote.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class TamingActivityResponse(
    @SerializedName("levels")
    val levels: List<Level>? =  emptyList()
)