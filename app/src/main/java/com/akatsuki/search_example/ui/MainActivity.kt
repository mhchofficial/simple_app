package com.akatsuki.search_example.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akatsuki.search_example.R
import com.akatsuki.search_example.databinding.ActivityMainBinding
import com.akatsuki.search_example.utils.show
import com.akatsuki.search_example.utils.hide
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var recyclerAdapter: Recycler_Adapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        recyclerAdapter = Recycler_Adapter()
        recyclerView = binding.RecyclerViewList


        mainViewModel.isLoading.observe(this) {
            Log.e("is true", it.toString())

            when (it) {
                true -> {
                    binding.RecyclerViewList.hide()
                    binding.progressCircular.show()

                }
                false -> {

                    binding.RecyclerViewList.show()
                    binding.progressCircular.hide()
                }
            }
        }


        val btn_search = binding.btnSearch
        val search_value = binding.SerachValue


        btn_search.setOnClickListener{
            search(search_value.text.toString())
        }





        recyclerView.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            //setHasFixedSize(true)
        }

        lists()



    }

    private fun search(query: String){
        mainViewModel.onSearch(query)
        mainViewModel.SearchResult.observe(this) {
            recyclerAdapter.products = it
        }
    }

    private fun lists(){
        mainViewModel.productModel.observe(this) {
            recyclerAdapter.products = it
        }
    }
}