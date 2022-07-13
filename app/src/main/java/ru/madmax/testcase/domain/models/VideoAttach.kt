package ru.madmax.testcase.domain.models

import com.google.gson.annotations.SerializedName

data class VideoAttach(
    @SerializedName("external_service")
    val external_service: ExternalService = ExternalService(),
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail = Thumbnail(),
    @SerializedName("time")
    val time: Int = 0,
    @SerializedName("width")
    val width: Int = 0
)