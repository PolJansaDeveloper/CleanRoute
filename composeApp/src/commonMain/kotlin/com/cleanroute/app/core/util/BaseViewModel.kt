package com.cleanroute.app.core.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

open class BaseViewModel {
    protected val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    fun clear() = scope.cancel()
}
