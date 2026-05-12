package com.cleanroute.app

import com.cleanroute.app.core.util.*
import kotlin.test.Test
import kotlin.test.assertTrue

class DateFormattersTest {
    @Test fun todayRange(){ assertTrue(endOfTodayMillis() > startOfTodayMillis()) }
}
