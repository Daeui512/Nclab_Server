package com.app.service

import com.app.data.UserInfo
import org.apache.catalina.User

interface UserService {
    fun allUsers(): List<UserInfo>
    fun oneUsers(empNo: String): List<UserInfo>
    fun insertUser(param: UserInfo): String
    fun updateUser(param: UserInfo): String
    fun deleteUser(param: UserInfo): String

    fun login(userId: String, password: String): String
    fun selectPassHash(userId: String, password: String): String
}