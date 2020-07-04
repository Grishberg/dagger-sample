package com.github.grishberg.daggersample.custompanel.common

import android.os.SystemClock
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.LinkedList

private const val TAG = "PerformanceReporter"

class LogcatPerformanceReporter(
    private val startAppTime: Long
) : PerformanceReporter {
    @Volatile
    private var isWarm = false
    private val names = LinkedList<String>()
    private val times = LinkedList<Long>()

    override fun startEvent(name: String) {
        times.add(SystemClock.uptimeMillis())
        names.add(name)
    }

    override fun endEvent() {
        val eventTime = SystemClock.uptimeMillis()
        val name = names.removeLast()
        val time = times.removeLast()
        GlobalScope.launch {
            val nameWithPrefix = "$name.${startTimeSuffix()}"
            Log.e(TAG, "event_duration: $nameWithPrefix: ${eventTime - time}")
            Log.e(TAG, "app_start_duration: $nameWithPrefix: ${eventTime - startAppTime}")
        }
    }

    override fun recordEventFromAppStart(name: String) {
        val eventTime = SystemClock.uptimeMillis()
        GlobalScope.launch {
            val nameWithPrefix = "$name.${startTimeSuffix()}"
            Log.e(TAG, "app_start_duration: $nameWithPrefix: ${eventTime - startAppTime}")
        }
    }

    private fun startTimeSuffix(): String = if (isWarm) "WARM" else "COLD"

    override fun markWarmStart() {
        isWarm = true
    }
}