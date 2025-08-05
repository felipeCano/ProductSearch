package com.test.productsearch.data.model

data class PulseBeacon(
    val bd: String,
    val bh: String,
    val enable: Boolean,
    val hostWithQM: String,
    val hostWithoutQM: String,
    val hostname: String,
    val photoHost: String,
    val photoHostWithoutQM: String
)