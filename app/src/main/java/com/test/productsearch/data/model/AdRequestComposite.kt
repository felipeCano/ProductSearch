package com.test.productsearch.data.model

data class AdRequestComposite(
    val adsConfig: String,
    val categoryId: String,
    val facets: String,
    val isDebug: Boolean,
    val isManualShelf: Boolean,
    val keyword: String
)