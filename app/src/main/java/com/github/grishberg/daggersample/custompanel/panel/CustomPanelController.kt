package com.github.grishberg.daggersample.custompanel.panel

import android.view.ViewStub
import javax.inject.Inject

class CustomPanelController @Inject constructor(
    private val dataSource: CustomPanelDataSource
) {
    private val observer = DataObserver()

    fun init(viewStub: ViewStub) {
        dataSource.addObserver(observer)
    }

    private inner class DataObserver: PanelDataObserver {
        override fun onDataReceived() {

        }
    }
}