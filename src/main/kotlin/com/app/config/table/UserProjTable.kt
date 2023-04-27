package com.app.config.table

import org.jetbrains.exposed.sql.Table

object UserProjTable : Table("USER_PROJ_INFO"){
    val projId = integer("PROJ_ID").autoIncrement()
    val empNo = varchar("EMP_NO", 50)
    val projTitle = varchar("PROJ_TITLE", 100)
    val projTech = varchar("PROJ_TECH", 50)
    val projDesc = varchar("PROJ_DESC", 100)

    override val primaryKey = PrimaryKey(UserProjTable.projId)
}