package com.cleanroute.app.core.util

import kotlin.random.Random

fun generateId(prefix: String = ""): String = "$prefix${nowMillis()}-${Random.nextInt(1000,9999)}"
