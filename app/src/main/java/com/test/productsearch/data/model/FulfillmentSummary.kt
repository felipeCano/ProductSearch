package com.test.productsearch.data.model

data class FulfillmentSummary(
    val deliveryDate: String,
    val fulfillment: String,
    val fulfillmentBadge: Any,
    val fulfillmentMethods: List<String>,
    val outOfCountryEligible: Any,
    val storeId: String
)