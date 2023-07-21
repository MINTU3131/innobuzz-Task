package com.mintusharma.innobuzz

import com.mintusharma.innobuzz.models.Post
import retrofit2.http.GET

interface ApiServices {

    @GET("posts")
    suspend fun getPosts(): List<Post>

}