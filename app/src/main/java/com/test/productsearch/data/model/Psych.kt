package com.test.productsearch.data.model

data class Psych(
    val browserName: String,
    val isAkamaiBot: String,
    val isBot: Boolean,
    val isDesktop: Boolean,
    val isMobile: Boolean,
    val previousAppVersion: String,
    val trafficType: String,
    val visitType: String
)