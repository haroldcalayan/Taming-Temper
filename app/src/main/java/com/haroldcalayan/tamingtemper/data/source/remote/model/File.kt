package com.haroldcalayan.tamingtemper.data.source.remote.model


import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class File(
    @SerializedName("url")
    @Expose
    val url: String?,
    @SerializedName("details")
    @Expose
    val details: Details?,
    @SerializedName("fileName")
    @Expose
    val fileName: String?,
    @SerializedName("contentType")
    @Expose
    val contentType: String?
)