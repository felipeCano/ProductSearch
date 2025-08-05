package com.test.productsearch.data.model

data class Debug(
    val adsUrl: String,
    val genAIDebugInfo: GenAIDebugInfo,
    val presoDebugInformation: List<PresoDebugInformation>,
    val responseTimeMillis: Int,
    val scsTimeMillis: Int,
    val sisUrl: String,
    val statusCode: String
)