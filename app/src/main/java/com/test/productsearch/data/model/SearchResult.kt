package com.test.productsearch.data.model

data class SearchResult(
    val aggregatedCount: Int,
    val catInfo: CatInfo,
    val categoryNavigation: List<Any?>,
    val count: Int,
    val debug: Debug,
    val enableFFAwareSearchBasedOnImplicitIntent: Boolean,
    val errorResponse: ErrorResponse,
    val gridItemsCount: Int,
    val hasMorePages: Boolean,
    val isModularSearch: Boolean,
    val itemStacks: List<ItemStack>,
    val navigationTokens: Any,
    val nonProduct: Any,
    val pac: Any,
    val pageMetadata: PageMetadataXX,
    val paginationV2: PaginationV2,
    val relatedSearch: List<RelatedSearch>,
    val requestContext: RequestContext,
    val searchRedirect: SearchRedirect,
    val spelling: Spelling,
    val title: String,
    val translation: Any
)