package com.github.grishberg.daggersample.custompanel.common

class ReporterFactoryImpl(
    private val appStart: Long
) : ReporterFactory {
    override fun create(): PerformanceReporter {
        return LogcatPerformanceReporter(appStart)
    }
}