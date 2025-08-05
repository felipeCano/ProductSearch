package com.test.productsearch.data.model

data class ErrorResponse(
    val correlationId: String,
    val errorCodes: Any,
    val errors: List<Any>,
    val source: String
)