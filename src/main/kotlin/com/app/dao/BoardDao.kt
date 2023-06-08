package com.app.dao

import com.app.config.table.BoardTable
import com.app.data.BoardInfo
import com.app.config.Connection
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BoardDao {
    companion object {
        fun selectBoardList(): List<BoardInfo> {
            Connection.connection()

            val boardList = transaction {
                addLogger(StdOutSqlLogger)

                BoardTable.selectAll().map {
                    BoardInfo(
                        boardNo = it[BoardTable.boardNo],
                        title = it[BoardTable.title],
                        content = it[BoardTable.content],
                        regUser = it[BoardTable.regUser],
                        regDate = it[BoardTable.regDate],
                        updUser = it[BoardTable.updUser],
                        updDate = it[BoardTable.updDate],
                        status = it[BoardTable.status],
                        viewCnt = it[BoardTable.viewCnt]
                    )
                }
            }
            return boardList
        }

        fun selectBoard(params: BoardInfo): List<BoardInfo> {
            Connection.connection()

            val board = transaction {
                addLogger(StdOutSqlLogger)

                BoardTable.select { BoardTable.boardNo eq params.boardNo }.map {
                        row -> BoardInfo(
                    boardNo = row[BoardTable.boardNo],
                    title = row[BoardTable.title],
                    content = row[BoardTable.content],
                    regUser = row[BoardTable.regUser],
                    regDate = row[BoardTable.regDate],
                    updUser = row[BoardTable.updUser],
                    updDate = row[BoardTable.updDate],
                    status = row[BoardTable.status],
                    viewCnt = row[BoardTable.viewCnt]
                )
                }
            }
            return board
        }

        fun insertBoard(params: BoardInfo): String {
            Connection.connection()
            var resultYn = "N"

            transaction {
                addLogger(StdOutSqlLogger)
//                val insertRow = BoardTable.insert {
//                    it[title] = params.title as? String ?: ""
//                    it[content] = params.content as? String ?: ""
//                    it[regUser] = params.regUser as? String ?: ""
//                    it[regDate] = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
//                    it[updUser] = params.regUser as? String ?: ""
//                    it[updDate] = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
//                    it[status] = "등록"
//                }.execute(this)

                val insertRow = BoardTable.insert() {
                    it[title] = params.title as? String ?: ""
                    it[content] = params.content as? String ?: ""
                    it[regUser] = params.regUser as? String ?: ""
                    it[regDate] = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    it[updUser] = params.regUser as? String ?: ""
                    it[updDate] = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    it[status] = "등록"
                }

//                if (insertRow == 1) {
                resultYn = "Y"
//                }
            }
            return resultYn
        }

        fun updateBoard(params: BoardInfo): String {
            Connection.connection()
            var resultYn = "N"

            transaction {
                addLogger(StdOutSqlLogger)
                val updateRow = BoardTable.update({ BoardTable.boardNo eq params.boardNo }) {
                    it[title] = params.title as? String ?: ""
                    it[content] = params.content as? String ?: ""
                    it[updUser] = params.updUser as? String ?: ""
                    it[updDate] = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    it[status] = "수정"
                }

                if (updateRow == 1) {
                    resultYn = "Y"
                }
            }
            return resultYn
        }

        fun deleteBoard(params: BoardInfo): String {
            Connection.connection()
            var resultYn = "N"

            transaction {
                addLogger(StdOutSqlLogger)
                val deleteRow = BoardTable.update({ BoardTable.boardNo eq params.boardNo }) {
                    it[updUser] = params.updUser as? String ?: ""
                    it[updDate] = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    it[status] = "삭제"
                }

                if (deleteRow == 1) {
                    resultYn = "Y"
                }
            }
            return resultYn
        }

        fun deleteBoard01(params: BoardInfo): String {
            Connection.connection()
            var resultYn = "N"

            transaction {
                addLogger(StdOutSqlLogger)
                val deleteRow = BoardTable.deleteWhere { BoardTable.boardNo eq params.boardNo }

                if (deleteRow == 1) {
                    resultYn = "Y"
                }
            }
            return resultYn
        }

        fun updateViewCnt(params: BoardInfo): String {
            Connection.connection()
            var resultYn = "N"

            transaction {
                addLogger(StdOutSqlLogger)
                val updateRow = BoardTable.update({ BoardTable.boardNo eq params.boardNo }) {
                    it[viewCnt] = params.viewCnt + 1
                }

                if (updateRow == 1) {
                    resultYn = "Y"
                }
            }
            return resultYn
        }
    }
}