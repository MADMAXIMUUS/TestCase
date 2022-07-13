package ru.madmax.testcase.domain.models

import com.google.gson.annotations.SerializedName

data class BlockItem(
    @SerializedName("image")
    val image: Image = Image(),
    @SerializedName("title")
    val title: String = ""
)