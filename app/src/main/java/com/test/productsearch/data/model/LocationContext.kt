package com.test.productsearch.data.model

data class LocationContext(
    val deliveryStore: String,
    val incatchment: Boolean,
    val intent: String,
    val pickupStore: String,
    val stateCode: String,
    val storeId: String,
    val zipCode: String
)