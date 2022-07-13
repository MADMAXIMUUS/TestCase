package ru.madmax.testcase.domain.models

import com.google.gson.annotations.SerializedName

data class Entry(
    @SerializedName("data")
    val postData: PostData = PostData(),
    @SerializedName("type")
    val type: String = ""
)