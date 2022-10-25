package com.akatsuki.search_example.data

import com.akatsuki.search_example.data.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface InterfaceApi {


    @GET("products")
    suspend fun ProductList(): Response<ResponseModel>

    @GET("products/search")
    suspend fun ProductSearch(
        @Query("q") q: String
    ): Response<ResponseModel>
}