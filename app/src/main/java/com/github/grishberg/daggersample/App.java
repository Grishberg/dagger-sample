package com.github.grishberg.daggersample;

import android.app.Application;

import com.github.grishberg.daggersample.custompanel.stub.CachedBitmapRepository;

public class App extends Application {
    private static CachedBitmapRepository sRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        sRepository = new CachedBitmapRepository(this);
    }

    public static CachedBitmapRepository cacheRepository() {
        return sRepository;
    }
}
