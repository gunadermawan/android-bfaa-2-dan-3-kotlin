package com.gunder.github.api

import com.gunder.github.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSetUsers(
        @Query("q") query: String
    ): Call<UserResponse>
}