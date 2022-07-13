package ru.madmax.testcase.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.madmax.testcase.domain.use_case.GetPostsUseCase
import ru.madmax.testcase.util.Paginator
import javax.inject.Inject

@HiltViewModel
class MainFeedViewModel @Inject constructor(
    private val getPostUseCases: GetPostsUseCase
) : ViewModel() {

    private val _pagingState = mutableStateOf(PagingState())
    val pagingState: State<PagingState> = _pagingState

    private val paginator = Paginator(
        onLoadUpdated = { isLoading ->
            _pagingState.value = pagingState.value.copy(
                isLoading = isLoading
            )
        },
        onRequest = { lastId, lastSortingValue ->
            getPostUseCases(lastId, lastSortingValue)
        },
        onSuccess = { posts ->
            _pagingState.value = pagingState.value.copy(
                items = pagingState.value.items + posts,
                endReached = posts.isEmpty(),
                isLoading = false
            )
        },
        onError = {

        }
    )

    init {
        loadNextPosts()
    }

    fun loadNextPosts() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }
}