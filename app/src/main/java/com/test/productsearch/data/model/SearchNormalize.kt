package com.test.productsearch.data.model

data class SearchNormalize(
    val analytics_log: AnalyticsLog,
    val brand: Brand,
    val normalized_query: String,
    val original_query: String,
    val productTypeSpecificityScore: ProductTypeSpecificityScore,
    val product_type: List<ProductType>,
    val rewritten_query: String,
    val specificity: String,
    val specificityScore: SpecificityScore,
    val top_query_cat_path: String,
    val top_query_cat_path_name: String,
    val verticalId: String
)