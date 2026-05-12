package com.cleanroute.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cleanroute.app.core.database.DatabaseDriverFactory
import com.cleanroute.app.core.di.initKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKoin(DatabaseDriverFactory(this))
        setContent { App() }
    }
}
