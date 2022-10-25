package com.akatsuki.search_example.repository

import com.akatsuki.search_example.data.InterfaceApi
import com.akatsuki.search_example.data.Resource
import com.akatsuki.search_example.data.model.ResponseModel
import javax.inject.Inject

class repository @Inject constructor(
    private val api: InterfaceApi
){

    suspend fun getProductList(): Resource<ResponseModel> {
        val response = try {
            api.ProductList().body()

        } catch (e: Exception) {
            return Resource.Errors("An unknown error occurred.")
        }
        return Resource.Success(response!!)
    }

    suspend fun getSearch(q: String): Resource<ResponseModel> {
        val response = try {
            api.ProductSearch(q).body()

        } catch (e: Exception) {
            return Resource.Errors("An unknown error occurred.")
        }
        return Resource.Success(response!!)
    }
}