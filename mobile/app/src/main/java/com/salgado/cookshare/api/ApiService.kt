package com.salgado.cookshare.api

import com.salgado.cookshare.model.User
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("/api/auth/login")
    fun login(@Body user: User): Call<User>

    @POST("/api/auth/register")
    fun register(@Body user: User): Call<User>

    @GET("/api/user/me")
    fun getProfile(@Query("userId") userId: Long): Call<User>

}