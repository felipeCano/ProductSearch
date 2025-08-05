package com.test.productsearch.data.model

data class VariantCriteria(
    val displayName: String,
    val id: Any,
    val isVariantTypeAllowed: Boolean,
    val isVariantTypeSwatch: Boolean,
    val name: String,
    val type: String,
    val variantList: List<Variant>
)