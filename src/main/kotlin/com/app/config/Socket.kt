package com.app.config

import io.ktor.websocket.*
import java.util.concurrent.atomic.AtomicInteger

class Socket(val session: DefaultWebSocketSession) {
    companion object{
        var lastId = AtomicInteger(0)
    }
    val name = "user${lastId.getAndIncrement()}"
}