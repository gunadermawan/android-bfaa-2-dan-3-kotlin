package com.gunder.github.api

import com.gunder.github.data.model.DetailUserResponse
import com.gunder.github.data.model.User
import com.gunder.github.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowersUser(
        @Path("username") username: String
    ):Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowingUser(
        @Path("username") username: String
    ):Call<ArrayList<User>>
}