package com.app.data

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val empNo: String,
    val empName: String,
    val mobile: String,
    val email: String,
    val address: String,
    val gender: String,
    val birthDate: String,
    val hireDate: String,
    val yearCnt: String,
    val registerDate: String,
    val status: String,
    val notes: String,
    val projId: String,
    val roleId: String,
    val userId: String,
    val password: String,
    var passwordHash: String
)

// Json으로 변환하여 화면쪽으로 뿌리주기위한 데이터 클래스 데이터액세스와 화면의 중간다리 역활( 각각의 데이터클래스를 만들어서 사용하자
//@Serializable // 데이터 클래스에 요녀석은 필수가 될듯 제이슨으로 변환시켜야하기 때문에
//data class User(val empNo: String, val empName: String, val email: String)