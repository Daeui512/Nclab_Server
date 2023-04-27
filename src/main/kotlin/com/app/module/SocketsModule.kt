package com.app.module

import com.app.config.Socket
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.time.Duration
import io.ktor.server.application.*
import io.ktor.server.routing.*
import java.util.*
import kotlin.collections.LinkedHashSet

fun Application.socketModule() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
    routing {
        val socket = Collections.synchronizedSet<Socket?>(LinkedHashSet())
        webSocket("/chat") {
            println("Adding users")
            val thisSocket = Socket(this)
            socket += thisSocket

            try {
                for (frame in incoming){
                    when (frame) {
                        is Frame.Text -> {
                            val receivedText = frame.readText()
                            val textWithUserName = "[${thisSocket.name}]: $receivedText"
                            socket.forEach{
                                it.session.send(textWithUserName)
                            }
                        }

                        else -> {}
                    }
                }

            } catch (e:Exception){
                e.printStackTrace()
            } finally {
                println("removing $thisSocket!")
                socket -= thisSocket
            }
        }


        webSocket("/ws") { // websocketSession
            for (frame in incoming) {
                if (frame is Frame.Text) {
                    val text = frame.readText()
                    outgoing.send(Frame.Text("YOU SAID: $text"))
                    if (text.equals("bye", ignoreCase = true)) {
                        close(CloseReason(CloseReason.Codes.NORMAL, "Client said BYE"))
                    }
                }
            }
        }
    }
}
