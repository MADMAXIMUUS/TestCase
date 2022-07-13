package ru.madmax.testcase.data.repository

import android.util.Log
import retrofit2.HttpException
import ru.madmax.testcase.data.remote.PostApi
import ru.madmax.testcase.domain.models.Response
import ru.madmax.testcase.domain.models.Result
import ru.madmax.testcase.domain.repository.PostRepository
import ru.madmax.testcase.util.Resource
import java.io.IOException
import javax.inject.Inject

class PostRepositoryImpl(
    private val api: PostApi
) : PostRepository {

    override suspend fun getPosts(
        lastId: Int,
        lastSortingValue: Int
    ): Resource<Response> {
        return try {
            val posts = api.getPosts(
                allSite = false,
                sorting = "date",
                subsitesIds = "237832",
                hashtag = "",
                lastId = lastId,
                lastSortingValue = lastSortingValue
            )
            Log.e("data", posts.toString())
            Resource.Success(data = posts)
        } catch (e: IOException) {
            Resource.Error(
                message = "Ошибка при загрузке ленты!"
            )
        } catch (e: HttpException) {
            Resource.Error(
                message = "Ой, что-то пошло не так!"
            )
        }
    }

}