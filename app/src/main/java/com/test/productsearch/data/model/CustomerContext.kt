package com.test.productsearch.data.model

data class CustomerContext(
    val customerId: Any,
    val isActiveMember: Boolean,
    val isPaidMember: Boolean,
    val paymentMethodMetaData: List<Any>,
    val purseTags: List<Any>
)