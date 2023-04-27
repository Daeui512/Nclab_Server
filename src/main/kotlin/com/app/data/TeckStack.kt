package com.app.data

import kotlinx.serialization.Serializable

@Serializable
data class TeckStack(
    val techId: String,
    val techName: String
)
