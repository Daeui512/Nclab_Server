package com.app.config.table

import org.jetbrains.exposed.sql.Table

object BoardTable : Table("BOARD_TABLE") {
    val boardNo = integer("BOARD_NO")
    val title = varchar("TITLE", 100)
    val content = varchar("CONTENT", 2000)
    val regUser = varchar("REG_USER", 50)
    val regDate = varchar("REG_DATE", 50)
    val updUser = varchar("UPD_USER", 50)
    val updDate = varchar("UPD_DATE", 50)
    val status = varchar("STATUS", 50)
    val viewCnt = integer("VIEW_CNT")

    override val primaryKey = PrimaryKey(boardNo)
}