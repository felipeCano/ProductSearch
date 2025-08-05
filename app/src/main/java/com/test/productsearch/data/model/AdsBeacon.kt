package com.test.productsearch.data.model

data class AdsBeacon(
    val adSlots: List<Any>,
    val adUuid: String,
    val max_ads: Int,
    val moduleInfo: String
)