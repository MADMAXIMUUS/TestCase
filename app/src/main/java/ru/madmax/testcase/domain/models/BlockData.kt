package ru.madmax.testcase.domain.models

import com.google.gson.annotations.SerializedName

data class BlockData(
    @SerializedName("items")
    val items: List<BlockItem> = emptyList(),
    @SerializedName("text")
    val text: String = "",
    @SerializedName("text_truncated")
    val text_truncated: String="",
    @SerializedName("title")
    val title: String="",
    @SerializedName("video")
    val video: Video = Video()
)