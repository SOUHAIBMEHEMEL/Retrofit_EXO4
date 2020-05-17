package com.example.android.retrofit_exo4

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface BlogService {

    @GET("posts/{id}")
    fun getPost(@Path("id") id: String): Call<Post>
}