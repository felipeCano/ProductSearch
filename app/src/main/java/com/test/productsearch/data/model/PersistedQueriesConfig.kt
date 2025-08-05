package com.test.productsearch.data.model

data class PersistedQueriesConfig(
    val allowList: List<String>,
    val blackList: List<String>,
    val enableAllowList: Boolean,
    val enablePersistedQueries: Boolean
)