package com.app.data

import kotlinx.serialization.Serializable

@Serializable
data class RoleInfo(
    val roleId: String,
    val roleA: String,
    val roleB: String
)