package com.test.productsearch.data.model

data class Brand(
    val extractedValue: String,
    val pcs_brand: List<String>,
    val score: Int
)