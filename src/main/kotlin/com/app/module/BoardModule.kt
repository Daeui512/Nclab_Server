package com.app.module

import com.app.data.BoardInfo
import com.app.service.imple.BoardServiceImple
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

fun Application.boardModule() {

    val boardService = BoardServiceImple()

//    install(ContentNegotiation) {
//        json()
//    }

    routing {
        route("/boards") {
            get("/getBoardList") {
                val boardList = boardService.selectBoardList()
                call.respond(Json.encodeToString(boardList))
            }

            post("/getBoard") {
                val params = call.receive<BoardInfo>()
                val board = boardService.selectBoard(params)
                call.respond(Json.encodeToString(board))
            }

            post("/insertBoard") {
                val params = call.receive<BoardInfo>()
                val resultYn = boardService.insertBoard(params)
            }

            post("/updateBoard") {
                val params = call.receive<BoardInfo>()
                val resultYn = boardService.updateBoard(params)
            }

            post("/deleteBoard") {
                val params = call.receive<BoardInfo>()
                val resultYn = boardService.deleteBoard(params)
            }

            post("/deleteBoard01") {
                val params = call.receive<BoardInfo>()
                val resultYn = boardService.deleteBoard01(params)
            }

            post("/updateViewCnt") {
                val params = call.receive<BoardInfo>()
                val resultYn = boardService.updateViewCnt(params)
            }
        }
    }
}