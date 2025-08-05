package com.test.productsearch.data.model

data class Module(
    val configs: Configs,
    val matchedTrigger: MatchedTrigger,
    val moduleId: String,
    val name: String,
    val type: String
)