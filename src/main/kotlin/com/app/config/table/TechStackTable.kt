package com.app.config.table

import org.jetbrains.exposed.sql.Table

object TechStackTable : Table("TECH_STACK") {
    val techId = integer("TECH_ID").autoIncrement()
    val techName = varchar("TECH_NAME", 50)

    override val primaryKey = PrimaryKey(TechStackTable.techId)
}