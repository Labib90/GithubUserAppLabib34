package com.example.githubuserapplabib.model

data class ResponseUserDetail(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val followers_url: String,
    val following_url: String,
    val name: String,
    val following: Int,
    val followers: Int
)
