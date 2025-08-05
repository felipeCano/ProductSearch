package com.test.productsearch.data.model

data class SkylineZone(
    val __typename: String,
    val configs: ConfigsXXX,
    val matchedTrigger: MatchedTrigger,
    val moduleId: String,
    val name: String,
    val schedule: Schedule,
    val type: String,
    val version: Int
)