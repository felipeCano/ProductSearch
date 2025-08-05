package com.test.productsearch.data.model

data class AdsContext(
    val brand: String,
    val categoryId: String,
    val categoryName: String,
    val dedupeList: List<String>,
    val itemId: String,
    val locationContext: LocationContext,
    val normKeyword: String,
    val productName: String,
    val productTypeId: String,
    val verticalId: String
)