package com.github.grishberg.daggersample.custompanel.common

interface PerformanceReporter {
    fun startEvent(name: String)
    fun endEvent()
    fun recordEventFromAppStart(name: String)
    fun markWarmStart()
}

interface ReporterFactory {
    fun create(): PerformanceReporter
}