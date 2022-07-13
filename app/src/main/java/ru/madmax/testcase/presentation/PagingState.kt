package ru.madmax.testcase.presentation

import ru.madmax.testcase.domain.models.Entry

data class PagingState(
    val items: List<Entry> = emptyList(),
    val isLoading: Boolean = false,
    val endReached: Boolean = false
)