package com.test.productsearch.data.model

data class ZoneV1(
    val extraData: ExtraData,
    val isNativeLazyLoaded: Boolean,
    val isP13nBtfModule: Boolean,
    val moduleId: String,
    val zone: String
)