package com.app.utils

import com.app.dao.UserInfoDao
import org.jetbrains.exposed.sql.Column
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class CommonUtils {
    companion object{
        /**
         *
         */
        fun encodePass(password: String?): String {
            val digest = MessageDigest.getInstance("SHA-256")
            val hash = digest.digest((password?.toByteArray(StandardCharsets.UTF_8) ?: "") as ByteArray?)
            return hash.fold("", { str, it -> str + "%02x".format(it) })
        }

        fun passChk(passwordHash: String, storedPasswordHash: String): Boolean {
            val digest = MessageDigest.getInstance("SHA-256")
            val enteredPasswordHash = digest.digest(passwordHash.toByteArray(StandardCharsets.UTF_8))
                .fold("", { str, it -> str + "%02x".format(it) })
            return enteredPasswordHash == storedPasswordHash
        }

        fun loginChk(userId: String, password: String): Boolean {
            val storedPasswordHash = UserInfoDao.selectPassHash(userId, password)
            return passChk(password, storedPasswordHash)
        }

        fun getRandomNum(): String {
            val random = java.util.Random()
            val randomNumber = (random.nextInt(900000) + 100000).toString()
            return randomNumber
        }
    }
}