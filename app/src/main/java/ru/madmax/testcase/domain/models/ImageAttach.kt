package ru.madmax.testcase.domain.models

import com.google.gson.annotations.SerializedName

data class ImageAttach(
    @SerializedName("color")
    val color: String = "",
    @SerializedName("external_service")
    val external_service: List<Any> = emptyList(),
    @SerializedName("hash")
    val hash: String = "",
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("size")
    val size: Int = 0,
    @SerializedName("type")
    val type: String = "",
    @SerializedName("uuid")
    val uuid: String = "",
    @SerializedName("width")
    val width: Int = 0
)