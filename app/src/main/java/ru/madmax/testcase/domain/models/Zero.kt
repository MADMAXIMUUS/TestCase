package ru.madmax.testcase.domain.models

import com.google.gson.annotations.SerializedName

data class Zero(
    @SerializedName("background")
    val background: String = "",
    @SerializedName("border")
    val border: String = "",
    @SerializedName("color")
    val color: String = "",
    @SerializedName("text")
    val text: String = "",
    @SerializedName("type")
    val type: String = ""
)