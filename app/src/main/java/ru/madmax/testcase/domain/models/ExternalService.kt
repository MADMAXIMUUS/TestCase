package ru.madmax.testcase.domain.models

import com.google.gson.annotations.SerializedName

data class ExternalService(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = ""
)