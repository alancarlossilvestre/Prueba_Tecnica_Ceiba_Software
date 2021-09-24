package com.example.myapplication.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIRest {

    @GET("users")
     fun getUser(): Call <List<User>>

    @GET("posts/")
    fun getAllPosts(): Call<List<Post>>

    @GET("posts/{userId}")
    fun getPostById(@Path("userId") id: Int): Call<Post>
}