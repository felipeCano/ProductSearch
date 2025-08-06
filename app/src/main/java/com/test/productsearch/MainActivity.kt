package com.test.productsearch

import android.R
import android.content.Context
import android.content.SharedPreferences
import android.database.Cursor
import android.database.MatrixCursor
import android.graphics.Color
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.productsearch.data.util.Resource
import com.test.productsearch.databinding.ActivityMainBinding
import com.test.productsearch.presentation.adapter.SearchProductAdapter
import com.test.productsearch.presentation.viewmodel.SearchProcuctViewModel
import com.test.productsearch.presentation.viewmodel.SearchProcuctViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val PREFS_NAME = "SearchHistory"
private const val KEY_SEARCH_QUERIES = "searchQueries"
private const val MAX_SEARCH_QUERIES = 5 // Limita el número de búsquedas guardadas

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

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.myToolbar)
        supportActionBar?.apply {
            title = "Search Product"

        }
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        viewModel = ViewModelProvider(this, factory)
            .get(SearchProcuctViewModel::class.java)
        initRecyclerView()
        setSearchView()
        setupSearchSuggestions()
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
            getSearchQueries()
            saveSearchQuery(query)
            viewSearchedNews()
        }

    }

    private fun saveSearchQuery(query: String) {
        val editor = sharedPreferences.edit()
        val gson = Gson()

        val existingQueriesJson = sharedPreferences.getString(KEY_SEARCH_QUERIES, null)
        val type = object : TypeToken<MutableList<String>>() {}.type
        val existingQueries: MutableList<String> = if (existingQueriesJson != null) {
            gson.fromJson(existingQueriesJson, type)
        } else {
            mutableListOf()
        }

        existingQueries.remove(query)

        existingQueries.add(0, query)

        while (existingQueries.size > MAX_SEARCH_QUERIES) {
            existingQueries.removeAt(existingQueries.size - 1)
        }

        val updatedQueriesJson = gson.toJson(existingQueries)
        editor.putString(KEY_SEARCH_QUERIES, updatedQueriesJson)
        editor.apply()
    }

    private fun setupSearchSuggestions() {
        val from = arrayOf(KEY_SEARCH_QUERIES)
        val to = intArrayOf(android.R.id.text1)

        val cursorAdapter = SimpleCursorAdapter(
            this,
            R.layout.simple_list_item_1,
            null,
            from,
            to,
            0
        )

        binding.svSearchProduct.suggestionsAdapter = cursorAdapter

        binding.svSearchProduct.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchQueries: List<String>
                if (newText.isNullOrEmpty()) {
                    searchQueries = getSearchQueries()
                } else{
                    searchQueries = getSearchQueries().filter {
                        it.contains(newText, ignoreCase = true)
                    }
                }

                val cursor = MatrixCursor(arrayOf(BaseColumns._ID, KEY_SEARCH_QUERIES))
                searchQueries.forEachIndexed { index, s ->
                    cursor.addRow(arrayOf(index, s))
                }
                cursorAdapter.changeCursor(cursor)
                return true
            }
        })

        binding.svSearchProduct.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            override fun onSuggestionClick(position: Int): Boolean {
                val cursor = binding.svSearchProduct.suggestionsAdapter.getItem(position) as Cursor
                val columnIndex = cursor.getColumnIndex(KEY_SEARCH_QUERIES)
                if (columnIndex != -1) {
                    val suggestion = cursor.getString(columnIndex)
                    binding.svSearchProduct.setQuery(suggestion, true)
                }
                return true
            }
        })
    }

    private fun getSearchQueries(): List<String> {
        val gson = Gson()
        val existingQueriesJson = sharedPreferences.getString(KEY_SEARCH_QUERIES, null)
        val type = object : TypeToken<MutableList<String>>() {}.type
        return if (existingQueriesJson != null) {
            gson.fromJson(existingQueriesJson, type)
        } else {
            emptyList()
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
                        if (it.item.props.pageProps.initialData.searchResult == null ||
                            it.item.props.pageProps.initialData.searchResult.itemStacks[0].count == 0) {
                            searchProductAdapter.differ.submitList(emptyList())
                            Toast.makeText(this, "Item not found", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            val itemStacks = it.item.props.pageProps.initialData.searchResult.itemStacks[0]
                            searchProductAdapter.differ.submitList(
                                itemStacks.items.toList()
                            )
                            val itemSize = itemStacks.items.toList().size
                            if (itemStacks.count % itemSize == 0) {
                                pages = itemStacks.count / itemSize
                            } else {
                                pages = itemStacks.count / itemSize + 1
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