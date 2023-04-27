package com.app.config.table

import org.jetbrains.exposed.sql.Table

object AuthInfoTable : Table("USER_AUTH_INFO"){
    val empNo = varchar("EMP_NO", 50)
    val loginTime = varchar("LOGIN_TIME", 50)
    val logoutTime = varchar("LOGOUT_TIME", 50)
    val authReqTime = varchar("AUTH_REQ_TIME", 50)
    val authKey = varchar("AUTH_KEY", 50)

    override val primaryKey = PrimaryKey(empNo)
}