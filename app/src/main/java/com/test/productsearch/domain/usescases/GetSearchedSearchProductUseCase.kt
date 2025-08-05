package com.test.productsearch.domain.usescases

import com.test.productsearch.data.model.APIResponse
import com.test.productsearch.data.util.Resource
import com.test.productsearch.domain.repository.SearchProductRepository

class GetSearchedSearchProductUseCase(private val searchProductRepository: SearchProductRepository) {

    suspend fun execute(searchQuery: String,page: Int): Resource<APIResponse> {
            return searchProductRepository.getSearchedSearchProduct(searchQuery,page)
    }
}