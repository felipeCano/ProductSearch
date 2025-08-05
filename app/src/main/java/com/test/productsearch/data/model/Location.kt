package com.test.productsearch.data.model

data class Location(
    val accessPointId: Any,
    val accessType: Any,
    val city: String,
    val deliveryStore: String,
    val intent: String,
    val pickupStore: String,
    val postalCode: String,
    val spokeNodeId: Any,
    val stateOrProvinceCode: String,
    val storeId: String
)