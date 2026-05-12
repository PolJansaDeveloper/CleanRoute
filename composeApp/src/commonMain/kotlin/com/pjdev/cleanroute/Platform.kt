package com.pjdev.cleanroute

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform