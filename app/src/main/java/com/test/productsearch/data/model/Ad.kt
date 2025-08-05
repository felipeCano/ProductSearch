package com.test.productsearch.data.model

data class Ad(
    val adContent: AdContent,
    val adRequestComposite: AdRequestComposite,
    val adsContext: AdsContext,
    val moduleConfigs: ModuleConfigs,
    val moduleType: String,
    val pageContext: PageContext,
    val pageId: String,
    val pageType: String,
    val platform: String,
    val stateCode: String,
    val status: String,
    val storeId: String,
    val zipCode: String
)