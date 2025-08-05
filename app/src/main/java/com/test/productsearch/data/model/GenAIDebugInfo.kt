package com.test.productsearch.data.model

data class GenAIDebugInfo(
    val genAIUnavailableReason: String,
    val isGenAiQueryEligible: Boolean,
    val reformulatedQuery: Any,
    val searchAlgorithm: String
)