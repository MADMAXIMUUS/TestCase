package ru.madmax.testcase.domain.models

import com.google.gson.annotations.SerializedName

data class CommentEditor(
    @SerializedName("enabled")
    val enabled: Boolean = false
)