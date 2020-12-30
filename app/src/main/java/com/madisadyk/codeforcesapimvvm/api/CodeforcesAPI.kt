package com.madisadyk.codeforcesapimvvm.api

import com.madisadyk.codeforcesapimvvm.models.ContestsResponse
import com.madisadyk.codeforcesapimvvm.models.HandlerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CodeforcesAPI {

    @GET("contest.list")
    suspend fun getContests(): Response<ContestsResponse>

    @GET("user.info")
    suspend fun getHandel(
        @Query("handles")
        handles: String = "varlokmaker"
    ): Response<HandlerResponse>
}