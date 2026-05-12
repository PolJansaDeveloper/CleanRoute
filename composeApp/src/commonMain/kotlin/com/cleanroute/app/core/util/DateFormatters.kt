package com.cleanroute.app.core.util

import kotlinx.datetime.*

fun nowMillis(): Long = Clock.System.now().toEpochMilliseconds()
fun startOfTodayMillis(): Long { val n = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date; return n.atStartOfDayIn(TimeZone.currentSystemDefault()).toEpochMilliseconds() }
fun endOfTodayMillis(): Long = startOfTodayMillis() + 86_399_999
fun isSameDayMillis(a: Long, b: Long): Boolean { val tz = TimeZone.currentSystemDefault(); return Instant.fromEpochMilliseconds(a).toLocalDateTime(tz).date == Instant.fromEpochMilliseconds(b).toLocalDateTime(tz).date }
fun formatTime(millis: Long): String = Instant.fromEpochMilliseconds(millis).toLocalDateTime(TimeZone.currentSystemDefault()).time.toString().take(5)
fun formatDate(millis: Long): String = Instant.fromEpochMilliseconds(millis).toLocalDateTime(TimeZone.currentSystemDefault()).date.toString()
fun formatDateTime(millis: Long): String = "${formatDate(millis)} ${formatTime(millis)}"
fun formatShortDate(millis: Long): String = formatDate(millis).drop(5)
