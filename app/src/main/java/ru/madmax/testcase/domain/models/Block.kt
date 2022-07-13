package ru.madmax.testcase.domain.models

import com.google.gson.annotations.SerializedName

data class Block(
    @SerializedName("anchor")
    val anchor: String="",
    @SerializedName("cover")
    val cover: Boolean = false,
    @SerializedName("data")
    val data: BlockData = BlockData(),
    @SerializedName("hidden")
    val hidden: Boolean = false,
    @SerializedName("type")
    val type: String = ""
)