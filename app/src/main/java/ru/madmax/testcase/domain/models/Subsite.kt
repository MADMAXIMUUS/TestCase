package ru.madmax.testcase.domain.models

import com.google.gson.annotations.SerializedName

data class Subsite(
    @SerializedName("avatar")
    val avatar: Avatar = Avatar(),
    @SerializedName("cover")
    val cover: Any = Any(),
    @SerializedName("description")
    val description: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("isAvailableForMessenger")
    val isAvailableForMessenger: Boolean = false,
    @SerializedName("isMuted")
    val isMuted: Boolean = false,
    @SerializedName("isOnline")
    val isOnline: Boolean = false,
    @SerializedName("isSubscribed")
    val isSubscribed: Boolean = false,
    @SerializedName("isUnsubscribable")
    val isUnsubscribable: Boolean = false,
    @SerializedName("isVerified")
    val isVerified: Boolean = false,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("prevEntry")
    val prevEntry: PrevEntry = PrevEntry(),
    @SerializedName("subtype")
    val subtype: String = "",
    @SerializedName("type")
    val type: Int = 0
)