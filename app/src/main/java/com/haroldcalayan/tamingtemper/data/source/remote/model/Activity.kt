package com.haroldcalayan.tamingtemper.data.source.remote.model


import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Activity(
    @SerializedName("id")
    @Expose
    val id: String?,
    @SerializedName("challengeId")
    @Expose
    val challengeId: String?,
    @SerializedName("type")
    @Expose
    val type: String?,
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("titleB")
    @Expose
    val titleB: String?,
    @SerializedName("description")
    @Expose
    val description: String?,
    @SerializedName("descriptionB")
    @Expose
    val descriptionB: String?,
    @SerializedName("state")
    @Expose
    val state: String?,
    @SerializedName("icon")
    @Expose
    val icon: Icon?,
    @SerializedName("lockedIcon")
    @Expose
    val lockedIcon: LockedIcon?
)