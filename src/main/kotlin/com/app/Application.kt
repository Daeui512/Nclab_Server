package com.app

import com.app.module.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.tomcat.*

fun main() {
    embeddedServer(Tomcat, port = 8090, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    //configureSerialization()
    //configureHTTP()s
    //configureSecurity()
    //configureRouting()

    userModule()     // domain ("/users/*")
    authModule()     // domain ("/auth/*")
    socketModule()   // domain ("/chat/*")
    boardModule()    // domain ("/boards/*")
}


