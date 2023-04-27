package com.app.config.table

import org.jetbrains.exposed.sql.Table

object RoleTable : Table("ACCESS_ROLE") {
    val roleId = integer("ROLE_ID").autoIncrement()
    val roleA = varchar("ROLE_A", 2)
    val roleB = varchar("ROLE_B", 2)

    override val primaryKey = PrimaryKey(RoleTable.roleId)
}