package com.app.config.table

import org.jetbrains.exposed.sql.Table

// 코틀린의 데이터베이스 연결을 위한 테이블 오브젝트를 생성하기 위한 객체 위의 데이터 객체와 비슷하지만 데이터베이스에 더 가까운 객체라고 생각하면 될듯 ( 각각의 테이블 오브젝트를 만들어서 사용하자
object UserInfoTable : Table("USER_BASE_INFO") {
    val empNo = varchar("EMP_NO", 50)
    val empName = varchar("EMP_NAME", 50)
    val email = varchar("EMAIL", 50)
    val mobile = varchar("MOBILE", 50)
    val address = varchar("ADDRESS", 50)
    val gender = varchar("GENDER", 50)
    val birthDate = varchar("BIRTH_DATE", 50)
    val hireDate = varchar("HIRE_DATE", 50)
    val yearCnt = varchar("YEAR_CNT", 50)
    val registerDate = varchar("REGISTER_DATE", 50)
    val status = varchar("STATUS", 50)
    val notes = varchar("NOTES", 50)
    val projId = varchar("PROJ_ID", 50)
    val roleId = varchar("ROLE_ID", 50)
    val userId = varchar("USER_ID", 50)
    val password = varchar("PASSWORD", 50)
    val passwordHash = varchar("PASSWORD_HASH", 50)

    override val primaryKey = PrimaryKey(empNo)
}