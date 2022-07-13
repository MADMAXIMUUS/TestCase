package ru.madmax.testcase.util

import android.util.Log
import ru.madmax.testcase.domain.models.Entry
import ru.madmax.testcase.domain.models.Response

class Paginator(
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (lastId: Int, lastSortingValue: Int) -> Resource<Response>,
    private val onError: suspend (String) -> Unit,
    private val onSuccess: (items: List<Entry>) -> Unit
) {
    private var lastId = 0
    private var lastSortingValue: Int =0

    suspend fun loadNextItems() {
        onLoadUpdated(true)
        when (val result = onRequest(lastId, lastSortingValue)) {
            is Resource.Success -> {
                Log.e("tag", result.data.toString())
                val response = result.data
                val items = response?.result?.items ?: emptyList()
                lastId = response?.result?.lastId ?: 0
                lastSortingValue = response?.result?.lastSortingValue ?: 0
                onSuccess(items)
                onLoadUpdated(false)
            }
            is Resource.Error -> {
                onError(result.data?.message ?: "Неизвестная ошибка")
                onLoadUpdated(false)
            }
        }
    }
}