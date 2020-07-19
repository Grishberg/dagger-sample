package com.github.grishberg.daggersample.custompanel.di

import com.github.grishberg.daggersample.custompanel.common.DimensionProvider
import com.github.grishberg.daggersample.custompanel.stub.CustomPanelStubDrawable
import dagger.Component

/**
 * Must declare dependencies to be imported as dependency in other components
 */
@FirstFrameScope
@Component(modules = [FirstFrameModule::class])
interface FirstFrameComponent {
    fun getDimensionProvider(): DimensionProvider
    fun getStubDrawable(): CustomPanelStubDrawable
}