package com.example.githubuserapplabib.api

import com.example.githubuserapplabib.model.ResponseUser
import com.example.githubuserapplabib.model.ResponseUserDetail
import com.example.githubuserapplabib.model.User
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
    ): Call<ResponseUser>

    @GET("user/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<ResponseUserDetail>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}