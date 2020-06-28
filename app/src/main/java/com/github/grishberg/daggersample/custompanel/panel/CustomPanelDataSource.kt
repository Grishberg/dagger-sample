package com.github.grishberg.daggersample.custompanel.panel

import javax.inject.Inject

interface PanelDataObserver {
    fun onDataReceived()
}

class CustomPanelDataSource @Inject constructor(){
    private val observers = mutableListOf<PanelDataObserver>()

    fun addObserver(observer: PanelDataObserver) {
        observers.add(observer)
    }

    fun removeObserver(observer: PanelDataObserver) {
        observers.remove(observer)
    }
}