package com.test.productsearch.data.model

data class OidcParams(
    val clientId: String,
    val isDev: Boolean,
    val profile: String,
    val redirectUri: String,
    val scope: String,
    val tenantId: String
)