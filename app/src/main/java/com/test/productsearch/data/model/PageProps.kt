package com.test.productsearch.data.model

data class PageProps(
    val adSessionId: String,
    val canValidateBeaconWithoutFiring: Boolean,
    val enableDcaPoc: Boolean,
    val enableGqlCsrRedirect: Boolean,
    val enableWcpBeacon: Boolean,
    val enableWcpBeaconDebug: Boolean,
    val excludeBeacon: Boolean,
    val initialData: InitialData,
    val isCaching: Boolean,
    val isMocksEnabled: Boolean,
    val isNextPublicMocksEnabled: Boolean,
    val isomorphicSessionId: String,
    val nonce: String,
    val overrideFSConfig: Boolean,
    val queryContextCacheId: Int,
    val renderViewId: String,
    val renderViewIdFromHeader: String,
    val reqHost: String,
    val ssrTraceparent: String,
)