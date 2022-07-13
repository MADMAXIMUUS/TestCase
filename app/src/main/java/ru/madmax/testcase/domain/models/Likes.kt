package ru.madmax.testcase.domain.models

import com.google.gson.annotations.SerializedName

data class Likes(
    @SerializedName("counter")
    val counter: Int = 0,
    @SerializedName("isHidden")
    val isHidden: Boolean = false,
    @SerializedName("isLiked")
    val isLiked: Int = 0,
    @SerializedName("summ")
    val summ: Int = 0
)