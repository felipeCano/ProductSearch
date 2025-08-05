package com.test.productsearch.data.repository.dataSource

import com.test.productsearch.data.model.APIResponse
import retrofit2.Response

interface SearchProductRemoteDataSource {

    suspend fun getSearchedSearchProduct(searchQuery:String, page: Int): Response<APIResponse>
}