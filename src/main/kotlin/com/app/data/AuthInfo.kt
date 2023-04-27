package com.app.data

import kotlinx.serialization.Serializable

@Serializable
data class AuthInfo(
    val empNo: String,
    val loginTime: String,
    val logoutTime: String,
    val authReqTime: String,
    var authKey: String
)