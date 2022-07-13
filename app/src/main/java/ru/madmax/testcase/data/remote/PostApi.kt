package ru.madmax.testcase.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.madmax.testcase.domain.models.Response

interface PostApi {

    @GET("timeline")
    suspend fun getPosts(
        @Query("allSite") allSite: Boolean,
        @Query("sorting") sorting: String,
        @Query("subsitesIds") subsitesIds: String,
        @Query("hashtag") hashtag: String,
        @Query("lastId") lastId: Int,
        @Query("lastSortingValue") lastSortingValue: Int
    ): Response

    companion object{
        const val BASE_URL = "https://api.tjournal.ru/v2.1/"
    }
}