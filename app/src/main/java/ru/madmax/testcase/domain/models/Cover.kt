package ru.madmax.testcase.domain.models

import com.google.gson.annotations.SerializedName

data class Cover(
    @SerializedName("data")
    val data: ImageAttach = ImageAttach(),
    @SerializedName("type")
    val type: String = ""
)