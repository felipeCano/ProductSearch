package com.test.productsearch.data.model

data class ItemStack(
    val content: Any,
    val count: Int,
    val description: String,
    val items: List<ItemX>,
    val layoutEnum: String,
    val queryUsedForSearchResults: String,
    val subTitle: Any,
    val title: String,
    val titleKey: String,
    val totalItemCountDisplay: String
)