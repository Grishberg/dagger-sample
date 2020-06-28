package com.github.grishberg.daggersample.custompanel.common

class CustomPanelState {
    /**
     * returns true when should show real view data and hide stub.
     */
    val isInitialized: Boolean
        get() = false
}