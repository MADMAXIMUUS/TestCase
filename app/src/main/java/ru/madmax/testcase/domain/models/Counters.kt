package ru.madmax.testcase.domain.models

import com.google.gson.annotations.SerializedName

data class Counters(
    @SerializedName("comments")
    val comments: Int = 0,
    @SerializedName("favorites")
    val favorites: Int = 0,
    @SerializedName("reposts")
    val reposts: Int = 0
)