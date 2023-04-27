package com.app.dao

import com.app.config.Connection
import com.app.config.table.UserInfoTable
import com.app.data.UserInfo
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class UserInfoDao {
    companion object{
        fun allUsers(): List<UserInfo> {
            Connection.connection()// DB Connection Generation

            val userInfoTest = transaction {
                addLogger(StdOutSqlLogger)

                UserInfoTable.selectAll().map {
                    UserInfo(
                        empNo        = it[UserInfoTable.empNo],
                        empName      = it[UserInfoTable.empName],
                        mobile       = it[UserInfoTable.mobile],
                        email        = it[UserInfoTable.email],
                        address      = it[UserInfoTable.address],
                        gender       = it[UserInfoTable.gender],
                        birthDate    = it[UserInfoTable.birthDate],
                        hireDate     = it[UserInfoTable.hireDate],
                        yearCnt      = it[UserInfoTable.yearCnt],
                        registerDate = it[UserInfoTable.registerDate],
                        status       = it[UserInfoTable.status],
                        notes        = it[UserInfoTable.notes],
                        projId       = it[UserInfoTable.projId],
                        roleId       = it[UserInfoTable.roleId],
                        userId = "",
                        password = "",
                        passwordHash = ""
                    )
                }
            }
            return userInfoTest
        }

        fun oneUsers(empNo: String): List<UserInfo> {
            Connection.connection()// DB Connection Generation

            val userInfo1 = transaction {
                addLogger(StdOutSqlLogger)

                UserInfoTable.select { UserInfoTable.empNo eq empNo }.map {
                    row -> UserInfo(
                        empNo        = row[UserInfoTable.empNo],
                        empName      = row[UserInfoTable.empName],
                        mobile       = row[UserInfoTable.mobile],
                        email        = row[UserInfoTable.email],
                        address      = row[UserInfoTable.address],
                        gender       = row[UserInfoTable.gender],
                        birthDate    = row[UserInfoTable.birthDate],
                        hireDate     = row[UserInfoTable.hireDate],
                        yearCnt      = row[UserInfoTable.yearCnt],
                        registerDate = row[UserInfoTable.registerDate],
                        status       = row[UserInfoTable.status],
                        notes        = row[UserInfoTable.notes],
                        projId       = row[UserInfoTable.projId],
                        roleId       = row[UserInfoTable.roleId],
                        userId = "",
                        password = "",
                        passwordHash = ""
                    )
                }
            }
            return userInfo1
        }

        // 단건 회원가입
        fun insertUser(params: UserInfo) : String{
            Connection.connection() // DB Connection generation
            var resultYn = "N"

            transaction {
                addLogger(StdOutSqlLogger)
                val insertRow: Any = UserInfoTable.insert {
                    it[empNo]          = params.empNo         // 사원번호
                    it[empName]        = params.empName       // 사원명
                    it[mobile]         = params.mobile        // 핸드폰번호
                    it[email]          = params.email         // 이메일
                    it[address]        = params.address       // 주소
                    it[gender]         = params.gender        // 성별
                    it[birthDate]      = params.birthDate     // 생년월일
                    it[hireDate]       = params.hireDate      // 입사일
                    it[yearCnt]        = params.yearCnt       // 연차
                    it[registerDate]   = params.registerDate  // 주민등록번호
                    it[status]         = params.status        // 상태
                    it[notes]          = params.notes         // 설명
                    it[userId]         = params.userId        // 아이디
                    it[password]       = params.password      // 패스워드
                    it[passwordHash]   = params.passwordHash  // 패스워드_암호화
                }.execute(this) as? Int ?: 0

                if (insertRow == 1){
                    resultYn = "Y"
                }
            }

            return resultYn
        }

        fun updateUser(params: UserInfo) : String{
            Connection.connection()
            var resultYn = "N"

            transaction {
                addLogger(StdOutSqlLogger)

                val updateRow: Int = UserInfoTable.update ({ (UserInfoTable.userId eq params.userId) and (UserInfoTable.passwordHash eq params.passwordHash) }){
                    it[empName]        = params.empName
                    it[mobile]         = params.mobile
                    it[email]          = params.email
                    it[address]        = params.address
                    it[gender]         = params.gender
                    it[birthDate]      = params.birthDate
                    it[hireDate]       = params.hireDate
                    it[yearCnt]        = params.yearCnt
                    it[registerDate]   = params.registerDate
                    it[status]         = params.status
                    it[notes]          = params.notes
                    it[userId]         = params.userId
                    it[password]       = params.password
                    it[passwordHash]   = params.passwordHash
                }

                if (updateRow > 0){
                    resultYn = "Y"
                }
            }

            return resultYn
        }

        fun deleteUser(params: UserInfo) : String{
            Connection.connection()
            var resultYn = "N"

            transaction {
                addLogger(StdOutSqlLogger)

                val deleteRow: Int = UserInfoTable.deleteWhere {UserInfoTable.empNo eq (params.empNo as? String?: "")}

                if (deleteRow > 0){
                    resultYn = "Y"
                }
            }
            return resultYn
        }

        // id/pw 로 hash 값을 가져온다.
        fun selectPassHash(userId: String, password: String) : String{
            Connection.connection()
            var passwordHash = ""

            transaction {
                addLogger(StdOutSqlLogger)
                UserInfoTable.slice(UserInfoTable.passwordHash).select {
                    (UserInfoTable.userId eq userId) and (UserInfoTable.password eq password)
                }.map { passwordHash = it[UserInfoTable.passwordHash] }
            }
            return passwordHash
        }

        fun login(userId: String, passwordHash: String): String {
            Connection.connection()
            var resultMsg = ""

            transaction {
                addLogger(StdOutSqlLogger)

                val userCnt = UserInfoTable.select {
                    (UserInfoTable.userId eq userId) and (UserInfoTable.passwordHash eq passwordHash)
                }.count().toInt()

                if (userCnt > 0){
                    resultMsg = "LOGIN SUCCESS"
                }
            }
            return resultMsg
        }
    }
}