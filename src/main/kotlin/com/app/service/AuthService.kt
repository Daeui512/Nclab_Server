package com.app.service

interface AuthService {
    /* 로그인/아웃 타임 저장 */
    fun logTime(empNo: String): Boolean
    /* 인증번호 발송 */
    fun sendAuthKey(empNo: String, mobile: String): Boolean
}