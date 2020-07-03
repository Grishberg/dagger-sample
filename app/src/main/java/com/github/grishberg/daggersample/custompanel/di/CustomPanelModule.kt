package com.github.grishberg.daggersample.custompanel.di

import android.app.Activity
import com.github.grishberg.daggersample.custompanel.common.DimensionProvider
import com.github.grishberg.daggersample.custompanel.panel.CustomPanelView
import com.github.grishberg.daggersample.custompanel.stub.CachedBitmapRepository
import dagger.Module
import dagger.Provides


@Module
class CustomPanelModule(
    private val activity: Activity,
    private val cachedBitmapRepository: CachedBitmapRepository
) {

    @CustomPanelScope
    @Provides
    fun provideCustomPanelView(dimensions: DimensionProvider): CustomPanelView {
        return CustomPanelView(activity, dimensions)
    }

    @CustomPanelScope
    @Provides
    fun provideActivity(): Activity = activity

    @CustomPanelScope
    @Provides
    fun provideCachedBitmapRepository(): CachedBitmapRepository = cachedBitmapRepository
}