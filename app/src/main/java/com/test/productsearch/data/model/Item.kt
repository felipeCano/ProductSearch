package com.test.productsearch.data.model

data class Item(
    val appGip: Boolean,
    val assetPrefix: String,
    val buildId: String,
    val customServer: Boolean,
    val defaultLocale: String,
    val dynamicIds: List<String>,
    val gip: Boolean,
    val isFallback: Boolean,
    val locale: String,
    val locales: List<String>,
    val page: String,
    val props: Props,
    val scriptLoader: List<Any?>
)