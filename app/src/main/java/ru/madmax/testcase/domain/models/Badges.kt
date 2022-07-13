package ru.madmax.testcase.domain.models

import com.google.gson.annotations.SerializedName

data class Badges(
    @SerializedName("*isEmptyValue")
    val isEmptyValue: List<Any> = emptyList(),
    @SerializedName("*items")
    val items: List<Any> = emptyList(),
    @SerializedName("0")
    val zero: Zero = Zero()
)