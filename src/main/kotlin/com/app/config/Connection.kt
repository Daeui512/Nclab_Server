package com.app.config

import org.jetbrains.exposed.sql.Database

class Connection{
    companion object {
        fun connection() {
            // 데이터베이스와 커넥션풀을 생성한다 각각의 파라미터를 프로퍼티화 또는 숨겨서 보안성을 강화하여야 한다. 그리고 데이터베이스에 대한 병렬성을 높일수 있을듯하다. 커넥션 별로 데이터베이스를 조절할수 있게 된다
            Database.connect(
                "jdbc:mysql://localhost:3306/NCLAB",
                driver = "com.mysql.cj.jdbc.Driver",
                user = "NCLAB",
                password = "Nclab0215!"
            )
        }
    }
}
