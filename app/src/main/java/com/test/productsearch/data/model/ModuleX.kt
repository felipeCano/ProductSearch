package com.test.productsearch.data.model

data class ModuleX(
    val __typename: String,
    val configs: ConfigsX,
    val matchedTrigger: MatchedTrigger,
    val moduleId: String,
    val name: String,
    val schedule: Schedule,
    val type: String,
    val version: Int
)