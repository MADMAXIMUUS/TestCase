package ru.madmax.testcase.domain.models

import com.google.gson.annotations.SerializedName

data class PrevEntry(
    @SerializedName("description")
    val description: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = ""
)