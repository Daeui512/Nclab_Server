package com.app.service.imple

import com.app.dao.UserInfoDao
import com.app.data.UserInfo
import com.app.service.UserService
import com.app.utils.CommonUtils

class UserServiceImple : UserService {

    /**
     * 조건없이 전체 직원정보를 조회한다.
     */
    override fun allUsers(): List<UserInfo> {
        return UserInfoDao.allUsers()
    }

    /**
     * empNo에 따른 직원정보를 조회한다.
     */
    override fun oneUsers(empNo: String): List<UserInfo> {
        return UserInfoDao.oneUsers(empNo)
    }

    /**
     * 직원정보를 저장한다.
     */
    override fun insertUser(param: UserInfo): String {
        param.passwordHash = CommonUtils.encodePass(param.password)
        return UserInfoDao.insertUser(param)
    }

    /**
     * 직원정보를 수정한다.
     */
    override fun updateUser(param: UserInfo): String {
        param.passwordHash = CommonUtils.encodePass(param.password)
        return UserInfoDao.updateUser(param)
    }

    /**
     * 직원정보를 삭제한다.
     */
    override fun deleteUser(param: UserInfo): String {
        return UserInfoDao.deleteUser(param)
    }

    /**
     * 로그인 기능을 수행한다.
     */
    override fun login(userId: String, password: String): String {
        return UserInfoDao.login(userId, CommonUtils.encodePass(password))
    }

    /**
     * userId, password 로 passwordHash 정보를 조회한다.
     */
    override fun selectPassHash(userId: String, password: String): String {
        return UserInfoDao.selectPassHash(userId, password)
    }
}