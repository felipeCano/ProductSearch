package com.test.productsearch.data.model

data class AdditionalQueryParams(
    val altQuery: String,
    val hidden_facet: Any,
    val isGenAiEnabled: Boolean,
    val isModuleArrayReq: Boolean,
    val isMoreOptionsTileEnabled: Boolean,
    val neuralSearchSeeAll: Boolean,
    val rootDimension: String,
    val selectedFilter: String,
    val translation: Any
)