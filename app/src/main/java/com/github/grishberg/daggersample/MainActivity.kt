package com.github.grishberg.daggersample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.github.grishberg.daggersample.custompanel.di.CustomPanelModule
import com.github.grishberg.daggersample.custompanel.di.DaggerCustomPanelComponent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val component = DaggerCustomPanelComponent
            .builder()
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