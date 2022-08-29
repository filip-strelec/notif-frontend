package com.example.notifapp

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @GET("/users")
    suspend fun getEmployees(): Response<ResponseBody>

}