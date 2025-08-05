package com.test.productsearch.data.model

data class RequestContext(
    val categories: Categories,
    val hasGicIntent: Boolean,
    val isFitmentFilterQueryApplied: Boolean,
    val searchMatchType: String,
    val selectedFacetCount: Int,
    val showComparisonCart: Boolean,
    val vertical: String
)