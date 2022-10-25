package com.akatsuki.search_example.useCases

import com.akatsuki.search_example.data.Resource
import com.akatsuki.search_example.data.model.Product
import com.akatsuki.search_example.data.model.ResponseModel
import com.akatsuki.search_example.repository.repository
import javax.inject.Inject

class getProductUseCase @Inject constructor(private val repository: repository) {
    suspend operator fun invoke(): Resource<ResponseModel> {
        return  repository.getProductList()

    }
}