package com.github.grishberg.daggersample;

import android.app.Application;
import android.os.SystemClock;

import com.github.grishberg.daggersample.custompanel.common.PerformanceReporter;
import com.github.grishberg.daggersample.custompanel.common.ReporterFactoryImpl;
import com.github.grishberg.daggersample.custompanel.stub.CachedBitmapRepository;

public class App extends Application {
    private static CachedBitmapRepository sRepository;
    private static PerformanceReporter sPerformanceReporter;
    private long appStartTime = SystemClock.uptimeMillis();

    @Override
    public void onCreate() {
        super.onCreate();
        setRepository(new CachedBitmapRepository(this));
        setPerformanceReporter(new ReporterFactoryImpl(appStartTime).create());
    }

    private static void setPerformanceReporter(PerformanceReporter performanceReporter) {
        sPerformanceReporter = performanceReporter;
    }

    private static void setRepository(CachedBitmapRepository cachedBitmapRepository) {
        sRepository = cachedBitmapRepository;
    }

    public static CachedBitmapRepository cacheRepository() {
        return sRepository;
    }

    public static PerformanceReporter performance() {
        return sPerformanceReporter;
    }
}
