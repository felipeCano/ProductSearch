package com.test.productsearch.data.repository

import com.test.productsearch.data.model.APIResponse
import com.test.productsearch.data.repository.dataSource.SearchProductRemoteDataSource
import com.test.productsearch.data.util.Resource
import com.test.productsearch.domain.repository.SearchProductRepository
import retrofit2.Response

class SearchProductRepositoryImpl(
    private val searchProductRemoteDataSource: SearchProductRemoteDataSource
):SearchProductRepository {

    override suspend fun getSearchedSearchProduct(
        searchQuery: String,
        page: Int
    ): Resource<APIResponse> {
        return responseToResource(
            searchProductRemoteDataSource.getSearchedSearchProduct(
                searchQuery,
                page
            )
        )
    }

    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}