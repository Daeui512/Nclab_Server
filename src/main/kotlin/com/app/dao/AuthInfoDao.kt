package com.app.dao

import com.app.config.Connection
import com.app.config.table.AuthInfoTable
import com.app.utils.DateUtil
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class AuthInfoDao {
    companion object{
        fun updateAuthKey(empNo: String, authKey: String): Boolean{
            Connection.connection()

            transaction {
                addLogger(StdOutSqlLogger)

                val updateRow: Int = AuthInfoTable.update ({AuthInfoTable.empNo eq empNo}){
                    it[AuthInfoTable.empNo] = empNo
                    it[AuthInfoTable.authKey] = authKey
                    it[AuthInfoTable.authReqTime] = DateUtil.dateString // "YYYYMMDD"
                }
            }

            return true
        }
    }
}