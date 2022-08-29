package com.example.notifapp.ApiServices

import com.example.notifapp.JsonModels.UserJsonModel
import retrofit2.Response
import retrofit2.http.*


interface APIServiceUsers {
    @GET("/users")
    suspend fun getUsers(): Response<List<UserJsonModel>>

}

interface APIServiceUser {
    @GET("/users/{user_name}")
    suspend fun getUser(@Path("user_name") user_name: String): Response<UserJsonModel>

}