package com.app.config.table

import org.jetbrains.exposed.sql.Table

object ChatMessageTable : Table("CHAT_MESSAGE") {
    val messageId = integer("MESSAGE_ID").autoIncrement()
    val senderId = varchar("SENDER_ID", 50)
    val receiverId = varchar("RECEIVER_ID", 50)
    val time = varchar("TIME", 50)
    val text = varchar("TEXT", 1000)

    override val primaryKey = PrimaryKey(messageId)
}
