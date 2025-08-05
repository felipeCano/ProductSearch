package com.test.productsearch.data.model

data class QueryContext(
    val appVersion: String,
    val gql: Gql,
    val rest: Rest
)