package com.test.productsearch.data.model

data class PageProperties(
    val affinityOverride: String,
    val cat_id: String,
    val department: String,
    val displayGuidedNav: Boolean,
    val grid: String,
    val itemStacks: ItemStacks,
    val itemStacksInterleavePosition: List<ItemStacksInterleavePosition>,
    val page: Int,
    val pageType: String,
    val pap: Pap,
    val prg: String,
    val ps: String,
    val query: String,
    val sort: String,
    val spelling: String,
    val stores: String
)