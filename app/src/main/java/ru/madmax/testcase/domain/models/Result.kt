package ru.madmax.testcase.domain.models

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("items")
    val items: List<Entry> = emptyList(),
    @SerializedName("lastId")
    val lastId: Int = 0,
    @SerializedName("lastSortingValue")
    val lastSortingValue: Int = 0
)