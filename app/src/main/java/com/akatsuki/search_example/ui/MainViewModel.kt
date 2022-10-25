package com.akatsuki.search_example.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akatsuki.search_example.data.Resource
import com.akatsuki.search_example.data.model.Product
import com.akatsuki.search_example.useCases.SearchUseCase
import com.akatsuki.search_example.useCases.getProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(private val getproductUseCase: getProductUseCase, private val searchUseCase: SearchUseCase): ViewModel(){

    val productModel = MutableLiveData<List<Product>>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        onCreate()
    }
    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getproductUseCase()

            if (result is Resource.Success) {
                productModel.postValue(result.data?.products!!)
                isLoading.postValue(false)
            }
        }
    }



    val SearchResult = MutableLiveData<List<Product>>()
    fun onSearch(q: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = searchUseCase.onCreate(q)
            if (result is Resource.Success) {
                SearchResult.postValue(result.data?.products!!)
                isLoading.postValue(false)
            }
        }
    }
}