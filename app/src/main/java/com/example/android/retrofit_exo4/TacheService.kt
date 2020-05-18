package com.example.android.retrofit_exo4

import retrofit2.Call
import retrofit2.http.GET

interface TacheService {
    @GET("/posts") fun get_list(): Call<List<Tache>>
}