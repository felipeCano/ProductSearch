package com.test.productsearch.data.model

data class AllSortAndFilterFacet(
    val displayType: Any,
    val expandOnLoad: Boolean,
    val isSelected: Any,
    val layout: String,
    val max: Int,
    val min: Int,
    val name: String,
    val selectedMax: Any,
    val selectedMin: Any,
    val stepSize: Int,
    val title: String,
    val type: String,
    val unboundedMax: Boolean,
    val urlParams: Any,
    val values: List<Value>
)