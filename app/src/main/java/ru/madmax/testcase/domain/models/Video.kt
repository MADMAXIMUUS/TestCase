package ru.madmax.testcase.domain.models

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("data")
    val data: VideoAttach = VideoAttach(),
    @SerializedName("type")
    val type: String = ""
)