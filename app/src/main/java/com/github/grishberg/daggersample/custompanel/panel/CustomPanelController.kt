package com.github.grishberg.daggersample.custompanel.panel

import android.app.Activity
import android.view.ViewGroup
import com.github.grishberg.daggersample.custompanel.stub.CustomPanelStubDrawable
import javax.inject.Inject

class CustomPanelController @Inject constructor(
    private val activity: Activity,
    private val dataSource: CustomPanelDataSource,
    private val customPanelView: CustomPanelView,
    private val stub: CustomPanelStubDrawable
) {
    private val observer = DataObserver()

    fun init(parent: ViewGroup, position: Int) {
        parent.addView(customPanelView, position)
        dataSource.addObserver(observer)
        customPanelView.background = stub
    }

    private inner class DataObserver : PanelDataObserver {
        override fun onDataReceived() {
            customPanelView.initIcons(dataSource.getData(activity))
        }
    }
}