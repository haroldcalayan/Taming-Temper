package com.haroldcalayan.tamingtemper.data.source.remote.model


import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Level(
    @SerializedName("level")
    @Expose
    val level: String?,
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("description")
    @Expose
    val description: String?,
    @SerializedName("state")
    @Expose
    val state: String?,
    @SerializedName("activities")
    @Expose
    val activities: List<Activity>?
)