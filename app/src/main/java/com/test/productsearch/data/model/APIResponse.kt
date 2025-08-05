package com.test.productsearch.data.model

data class APIResponse(
    val domainCode: String,
    val item: Item,
    val keyword: String,
    val responseMessage: String,
    val responseStatus: String,
    val sortStrategy: String
)