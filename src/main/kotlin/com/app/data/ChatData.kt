package com.app.data

import kotlinx.serialization.Serializable

@Serializable
data class ChatData(
    val meesgeId: String,
    val roomId: String,
    val senderId: String,
    val receiverId: String,
    val time: String,
    val text: String
)
