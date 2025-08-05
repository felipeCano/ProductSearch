package com.test.productsearch.data.model

data class Context(
    val createAccountPathname: String,
    val createMembershipPathName: String,
    val csrDisabledPaths: List<Any?>,
    val defaultCountryCode: String,
    val defaultCountryISOCode: String,
    val disableVerifyLaterPage: Boolean,
    val enableRobotsTag: Boolean,
    val footerOptions: List<Any?>,
    val hideFooter: Boolean,
    val marketingEmails: Boolean,
    val privacyLink: String,
    val rightsReservedText: String,
    val selectPhoneNumberPathName: String,
    val shouldSkipPhoneCollectionForDays: Boolean,
    val signInPathname: String,
    val signOutUrl: String,
    val successPathName: String,
    val tenants: List<Tenant>,
    val termsLink: String,
    val updatePhoneNumberPathName: String,
    val verifyItsYouPage: String,
    val verifyItsYouPageByOtpPathName: String
)