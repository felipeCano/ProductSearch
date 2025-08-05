package com.test.productsearch.domain.repository

import com.test.productsearch.data.model.APIResponse
import com.test.productsearch.data.util.Resource

interface SearchProductRepository {

    suspend fun getSearchedSearchProduct(searchQuery:String, page: Int): Resource<APIResponse>
}