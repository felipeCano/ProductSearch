package com.test.productsearch

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.test.productsearch.data.util.Resource
import com.test.productsearch.presentation.viewmodel.SearchProcuctViewModel
import com.test.productsearch.presentation.viewmodel.SearchProcuctViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: SearchProcuctViewModelFactory
    lateinit var viewModel: SearchProcuctViewModel
    private var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, factory)
            .get(SearchProcuctViewModel::class.java)
        setSearchView()
    }

    private fun setSearchView(){
        viewModel.searchProduct("sony",page)
        viewSearchedNews()
    }
    fun viewSearchedNews(){
        viewModel.searchproduct.observe(this, { response ->
            when (response) {
                is Resource.Success -> {
                    //hideProgressBar()
                    response.data?.let {
                        Log.d("MYTAG", "came here ${it.item.props.pageProps.initialData.searchResult.itemStacks.toList().size}")
//                        newsAdapter.differ.submitList(it.articles.toList())
//                        if (it.totalResults % 20 == 0) {
//                            pages = it.totalResults / 20
//                        } else {
//                            pages = it.totalResults / 20 + 1
//                        }
//                        isLoading = page == pages
                    }
                }

                is Resource.Error -> {
//                    hideProgressBar()
                    response.message?.let {
                        Log.d("MYTAG", "came here $it")
                        Toast.makeText(this, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }


                }

                is Resource.Loading -> {
//                    showProgressBar()
                }
            }
        })
    }
}