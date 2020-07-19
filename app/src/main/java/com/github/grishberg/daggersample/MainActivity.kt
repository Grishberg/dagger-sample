package com.github.grishberg.daggersample

import android.os.Bundle
import android.view.Choreographer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import com.github.grishberg.daggersample.custompanel.common.PerformanceEvents
import com.github.grishberg.daggersample.custompanel.di.CustomPanelModule
import com.github.grishberg.daggersample.custompanel.di.DaggerCustomPanelComponent
import com.github.grishberg.daggersample.custompanel.di.DaggerFirstFrameComponent
import com.github.grishberg.daggersample.custompanel.di.FirstFrameModule

class MainActivity : AppCompatActivity() {
    private var isWarm = true

    override fun onCreate(savedInstanceState: Bundle?) {
        isWarm = savedInstanceState == null
        if (isWarm) {
            App.performance().markWarmStart()
        }
        App.performance().recordEventFromAppStart(PerformanceEvents.ACTIVITY_ON_CREATE)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rootView = window.decorView.findViewById<View>(android.R.id.content)
        rootView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                App.performance().recordEventFromAppStart(PerformanceEvents.ON_PREDRAW)
                rootView.viewTreeObserver.removeOnPreDrawListener(this)
                return true
            }
        })

        val drawListener = object : ViewTreeObserver.OnDrawListener {
            override fun onDraw() {
                Choreographer.getInstance().postFrameCallback {
                    App.performance().recordEventFromAppStart(PerformanceEvents.ON_DRAW)
                    rootView.viewTreeObserver.removeOnDrawListener(this)
                }
            }
        }
        rootView.viewTreeObserver.addOnDrawListener(drawListener)
        val firstFrameComponent = DaggerFirstFrameComponent
            .builder()
            .firstFrameModule(FirstFrameModule(this))
            .build()

        val component = DaggerCustomPanelComponent
            .builder()
            .firstFrameComponent(firstFrameComponent)
            .customPanelModule(CustomPanelModule(this, App.cacheRepository()))
            .build()
        val controller = component.getPanelController()
        val content = findViewById<ViewGroup>(R.id.content)
        controller.init(content, 2)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}