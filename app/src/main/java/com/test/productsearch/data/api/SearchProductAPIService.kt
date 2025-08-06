package com.test.productsearch.data.api

import com.test.productsearch.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchProductAPIService {

    @GET("wlm/walmart-search-by-keyword")
    suspend fun getSearchedSearchProduct(
        @Query("keyword")
        searchQuery: String,
        @Query("page")
        pages: Int,
    ): Response<APIResponse>
}