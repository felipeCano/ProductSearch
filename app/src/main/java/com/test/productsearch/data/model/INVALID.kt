package com.test.productsearch.data.model

data class INVALID(
    val __typename: String,
    val configs: ConfigsXX,
    val matchedTrigger: MatchedTrigger,
    val moduleId: String,
    val name: String,
    val schedule: Schedule,
    val type: String,
    val version: Int
)