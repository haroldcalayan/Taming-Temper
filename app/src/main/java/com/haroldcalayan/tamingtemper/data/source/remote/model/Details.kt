package com.haroldcalayan.tamingtemper.data.source.remote.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Details(
    @SerializedName("size")
    @Expose
    val size: Int
)