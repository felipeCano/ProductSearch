package com.test.productsearch.data.model

data class Group(
    val assetUrl: String,
    val groupTitle: String,
    val groupType: String,
    val nearByStores: Any,
    val services: List<Service>,
    val shortDescription: Any,
    val unavailabilityReason: Any
)