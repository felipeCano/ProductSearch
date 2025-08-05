package com.test.productsearch.data.model

data class AddOnService(
    val groups: List<Group>,
    val serviceProviders: List<Any>,
    val serviceSubTitle: String,
    val serviceTitle: String,
    val serviceType: String
)