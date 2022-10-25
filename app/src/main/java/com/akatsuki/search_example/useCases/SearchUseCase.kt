package com.akatsuki.search_example.useCases

import com.akatsuki.search_example.data.Resource
import com.akatsuki.search_example.data.model.Product
import com.akatsuki.search_example.data.model.ResponseModel
import com.akatsuki.search_example.repository.repository
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: repository) {

    //private var _query: String = ""

    suspend fun onCreate(_query: String): Resource<ResponseModel> {
        return  repository.getSearch(_query)

    }
}