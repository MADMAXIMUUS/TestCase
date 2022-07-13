package ru.madmax.testcase.domain.models

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("message")
    val message: String = "",
    @SerializedName("result")
    val result: Result = Result()
)