package ru.madmax.testcase.domain.use_case

import ru.madmax.testcase.domain.models.Response
import ru.madmax.testcase.domain.repository.PostRepository
import ru.madmax.testcase.util.Resource
import javax.inject.Inject

class GetPostsUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(
        lastId: Int,
        lastSortingValue: Int
    ): Resource<Response> {
        return repository.getPosts(
            lastId,
            lastSortingValue
        )
    }
}