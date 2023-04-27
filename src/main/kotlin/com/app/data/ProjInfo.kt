package com.app.data

import kotlinx.serialization.Serializable

@Serializable
data class ProjInfo(
    val projId: String,
    val empNo: String,
    val projTitle: String,
    val projTech: String,
    val projDesc: String
)
