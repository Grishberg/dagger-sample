package com.github.grishberg.daggersample.custompanel.di

import com.github.grishberg.daggersample.custompanel.panel.CustomPanelController
import dagger.Component

@CustomPanelScope
@Component(modules = [CustomPanelModule::class])
internal interface CustomPanelComponent {
    fun getPanelController(): CustomPanelController
}