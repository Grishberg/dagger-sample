package com.github.grishberg.daggersample.custompanel.di

import com.github.grishberg.daggersample.custompanel.panel.CustomPanelController
import dagger.Component

@Component
interface CustomPanelComponent {
    fun getController() : CustomPanelController
}