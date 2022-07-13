package ru.madmax.testcase.domain.repository

import ru.madmax.testcase.domain.models.Response
import ru.madmax.testcase.util.Resource

interface PostRepository {

    suspend fun getPosts(
        lastId: Int,
        lastSortingValue: Int
    ): Resource<Response>
}