package com.app.service.imple

import com.app.dao.AuthInfoDao
import com.app.data.AuthInfo
import com.app.service.AuthService
import com.app.utils.CommonUtils
import org.jetbrains.exposed.sql.Column

class AuthServiceImple: AuthService {

    override fun logTime(empNo: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun sendAuthKey(empNo: String, mobile: String): Boolean {
        val authKey = CommonUtils.getRandomNum()
        return AuthInfoDao.updateAuthKey(empNo, authKey)
    }
}