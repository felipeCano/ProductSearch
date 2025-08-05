package com.test.productsearch.data.repository.dataSourceImpl

import com.test.productsearch.data.api.SearchProductAPIService
import com.test.productsearch.data.model.APIResponse
import com.test.productsearch.data.repository.dataSource.SearchProductRemoteDataSource
import retrofit2.Response

class SearchProductRemoteDataSourceImpl(
    private val searchProductAPIService: SearchProductAPIService
) : SearchProductRemoteDataSource {
    override suspend fun getSearchedSearchProduct(
        searchQuery: String,
        page: Int
    ): Response<APIResponse> {
        return searchProductAPIService.getSearchedSearchProduct(searchQuery,page)
    }


}