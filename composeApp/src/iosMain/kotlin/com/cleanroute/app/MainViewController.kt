package com.cleanroute.app

import androidx.compose.ui.window.ComposeUIViewController
import com.cleanroute.app.core.database.DatabaseDriverFactory
import com.cleanroute.app.core.di.initKoin

fun MainViewController() = ComposeUIViewController {
    initKoin(DatabaseDriverFactory())
    App()
}
