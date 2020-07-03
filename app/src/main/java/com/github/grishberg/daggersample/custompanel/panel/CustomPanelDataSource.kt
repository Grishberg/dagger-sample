package com.github.grishberg.daggersample.custompanel.panel

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Handler
import androidx.core.content.ContextCompat
import com.github.grishberg.daggersample.R
import javax.inject.Inject

interface PanelDataObserver {
    fun onDataReceived()
}

class CustomPanelDataSource @Inject constructor() {
    private val observers = mutableListOf<PanelDataObserver>()
    val handler = Handler()

    init {
        handler.postDelayed({ notifyDataReceived() }, 3000)
    }

    private fun notifyDataReceived() {
        observers.forEach { it.onDataReceived() }
    }

    fun addObserver(observer: PanelDataObserver) {
        observers.add(observer)
    }

    fun removeObserver(observer: PanelDataObserver) {
        observers.remove(observer)
    }

    fun getData(context: Context): List<Drawable> {
        //TODO: download from another thread.
        val icons = mutableListOf<Drawable>()
        icons.add(ContextCompat.getDrawable(context, R.drawable.ic_data_icon1)!!)
        icons.add(ContextCompat.getDrawable(context, R.drawable.ic_data_icon2)!!)
        icons.add(ContextCompat.getDrawable(context, R.drawable.ic_data_icon3)!!)
        icons.add(ContextCompat.getDrawable(context, R.drawable.ic_data_icon4)!!)
        icons.add(ContextCompat.getDrawable(context, R.drawable.ic_data_icon5)!!)
        return icons
    }
}