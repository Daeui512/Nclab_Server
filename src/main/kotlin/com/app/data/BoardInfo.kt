package com.app.data

import kotlinx.serialization.Serializable

@Serializable
data class BoardInfo(
    val boardNo: Int,
    val title: String,
    val content: String,
    val regUser: String,
    val regDate: String,
    val updUser: String,
    val updDate: String,
    val status: String,
    val viewCnt: Int
)