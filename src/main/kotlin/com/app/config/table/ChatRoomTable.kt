package com.app.config.table

import org.jetbrains.exposed.sql.Table

object ChatRoomTable : Table("CHAT_ROOM") {
    val roomId = integer("ROOM_ID").autoIncrement()
    val senderId = varchar("SENDER_ID", 50)
    val receiverId = varchar("RECEIVER_ID", 50)

    override val primaryKey = PrimaryKey(ChatRoomTable.roomId)
}