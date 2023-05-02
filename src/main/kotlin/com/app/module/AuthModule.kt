package com.app.module

import com.app.data.AuthInfo
import com.app.data.UserInfo
import com.app.service.imple.AuthServiceImple
import com.app.service.imple.UserServiceImple
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun Application.authModule() {

    val authService = AuthServiceImple()
    val userService = UserServiceImple()

    //install(ContentNegotiation){
    //    json()
    //}
    //install(StatusPages) {
    //        exception<Throwable> { call, cause ->
    //        call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
    //    }
    //}

    routing {
        route("/auth"){
            put("/logTime") {
                try {
                    val flag = call.receive<AuthInfo>()

                }catch (e:Exception) {
                    e.printStackTrace()
                }
            }

            put("/send") {
                try {
                    val param = call.receive<AuthInfo>()
                    val userInfoOut: List<UserInfo> = userService.oneUsers(param.empNo)
                    val result = authService.sendAuthKey(param.empNo, userInfoOut[0].mobile)
                    call.respond(Json.encodeToString(result))
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }

        }
    }
}