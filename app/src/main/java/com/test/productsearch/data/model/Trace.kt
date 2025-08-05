package com.test.productsearch.data.model

data class Trace(
    val spanId: String,
    val traceFlags: Int,
    val traceId: String
)