package com.test.productsearch

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.productsearch.data.util.Resource
import com.test.productsearch.databinding.ActivityMainBinding
import com.test.productsearch.presentation.adapter.SearchProductAdapter
import com.test.productsearch.presentation.viewmodel.SearchProcuctViewModel
import com.test.productsearch.presentation.viewmodel.SearchProcuctViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: SearchProcuctViewModelFactory

    @Inject
    lateinit var searchProductAdapter: SearchProductAdapter
    lateinit var viewModel: SearchProcuctViewModel
    private var page = 1
    private var pages = 0
    private var isScrolling = false
    private var isLastPage = false
    private var isLoading = false
    var keyword = ""
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            binding.myToolbar.setTitle("Search Product")
            binding.myToolbar.setTitleTextColor(Color.WHITE)

        }
        viewModel = ViewModelProvider(this, factory)
            .get(SearchProcuctViewModel::class.java)
        initRecyclerView()
        setSearchView()
    }

    private fun initRecyclerView() {
        binding.rvSearchProduct.apply {
            adapter = searchProductAdapter
            layoutManager = LinearLayoutManager(context)
            addOnScrollListener(this@MainActivity.onScrollListener)
        }
    }

    private fun setSearchView() {
        binding.btnAcceptSearch.setOnClickListener {
            val query =
                binding.svSearchProduct.query.toString()

            keyword = query
            viewModel.searchProduct(query, page)
            viewSearchedNews()
        }

    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.rvSearchProduct.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
            val shoulPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            if (shoulPaginate) {
                page++
                viewModel.searchProduct(keyword, page)
                isScrolling = false
            }
        }
    }

    fun viewSearchedNews() {
        viewModel.searchproduct.observe(this, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        if (it.item.props.pageProps.initialData.searchResult == null) {
                            searchProductAdapter.differ.submitList(emptyList())
                            Toast.makeText(this, "Item not found", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            searchProductAdapter.differ.submitList(
                                it.item
                                    .props.pageProps.initialData.searchResult.itemStacks[0].items.toList()
                            )
                            if (it.item.props.pageProps.initialData.searchResult.itemStacks[0].count % 40 == 0) {
                                pages =
                                    it.item.props.pageProps.initialData.searchResult.itemStacks[0].count / 40
                            } else {
                                pages =
                                    it.item.props.pageProps.initialData.searchResult.itemStacks[0].count / 40 + 1
                            }
                            isLastPage = page >= pages
                        }
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Log.d("MYTAG", "came here $it")
                        Toast.makeText(this, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun showProgressBar() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.progressBar.visibility = View.INVISIBLE
    }
}