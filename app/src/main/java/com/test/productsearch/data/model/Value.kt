package com.test.productsearch.data.model

data class Value(
    val baseSeoURL: String,
    val catPathName: String,
    val description: Any,
    val expandOnLoad: Boolean,
    val id: String,
    val isSelected: Boolean,
    val itemCount: Int,
    val name: String,
    val title: String,
    val type: String,
    val values: Any
)