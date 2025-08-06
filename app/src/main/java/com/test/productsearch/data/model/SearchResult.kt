package com.test.productsearch.data.model

data class SearchResult(
    val aggregatedCount: Int,
    val categoryNavigation: List<Any?>,
    val count: Int,
    val enableFFAwareSearchBasedOnImplicitIntent: Boolean,
    val gridItemsCount: Int,
    val hasMorePages: Boolean,
    val isModularSearch: Boolean,
    val itemStacks: List<ItemStack>,
    val navigationTokens: Any,
    val nonProduct: Any,
    val pac: Any,
    val title: String,
    val translation: Any
)