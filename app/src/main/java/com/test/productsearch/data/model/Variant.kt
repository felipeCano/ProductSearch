package com.test.productsearch.data.model

data class Variant(
    val availabilityStatus: String,
    val displayName: String,
    val id: Any,
    val images: List<String>,
    val name: String,
    val products: List<String>,
    val rank: Int,
    val selectedProduct: SelectedProduct,
    val swatchImageUrl: String
)