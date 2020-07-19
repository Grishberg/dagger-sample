package com.github.grishberg.daggersample.custompanel.di

import android.app.Activity
import com.github.grishberg.daggersample.custompanel.common.DimensionProvider
import com.github.grishberg.daggersample.custompanel.panel.CustomPanelView
import dagger.Module
import dagger.Provides

@Module
class FirstFrameModule(
    private val activity: Activity
) {
    @FirstFrameScope
    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @FirstFrameScope
    @Provides
    fun provideCustomPanelView(dimensions: DimensionProvider): CustomPanelView {
        return CustomPanelView(activity, dimensions)
    }

}