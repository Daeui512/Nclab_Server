package com.app.module

import com.app.data.UserInfo
import com.app.service.imple.UserServiceImple
import com.app.utils.CommonUtils
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun Application.userModule() {

    val userService = UserServiceImple()

    install(ContentNegotiation){
        json()
    }
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }


    routing {
        route("/users"){
            get("/all") {
                try {
                    val users = userService.allUsers()
                    call.respond(Json.encodeToString(users))
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }

            get("/one"){
                try {
                    val empNo = call.parameters["empNo"]
                    val userOne = empNo?.let { it1 -> userService.oneUsers(it1) }
                    call.respond(Json.encodeToString(userOne))
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }

            post("/signUp") {
                try {
                    val param = call.receive<UserInfo>()
                    val registYn = userService.insertUser(param)
                    call.respond((Json.encodeToString(registYn)))
                }catch(e:Exception){
                    e.printStackTrace()
                }
            }

            put("/upd") {
                try {
                    val param = call.receive<UserInfo>()
                    val updateYn = userService.updateUser(param)
                    call.respond(Json.encodeToString(updateYn))
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }

            delete("/del") {
                try {
                    val param = call.receive<UserInfo>()
                    val deleteYn = userService.deleteUser(param)
                    call.respond(Json.encodeToString(deleteYn))
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }

            post("/login") {

                val params = call.receiveParameters()

                val userId : String = params["userId"] ?:""
                val password : String = params["password"] ?:""
                /**
                 * 패스워드 체크 및 hash 체크
                 */
                if(CommonUtils.loginChk(userId, password)){
                    val loginUser = userService.login(userId, password)
                    call.respond(Json.encodeToString(loginUser))
                }
            }
        }
    }
}
