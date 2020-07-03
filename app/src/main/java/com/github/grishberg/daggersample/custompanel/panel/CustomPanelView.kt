package com.github.grishberg.daggersample.custompanel.panel

import android.app.Activity
import android.graphics.drawable.Drawable
import android.widget.FrameLayout
import android.widget.ImageView
import com.github.grishberg.daggersample.R
import com.github.grishberg.daggersample.custompanel.common.DimensionProvider
import javax.inject.Inject

/**
 * heavy view
 */
class CustomPanelView @Inject constructor(
    activity: Activity,
    private val dimensions: DimensionProvider
) : FrameLayout(activity) {

    private val buttons = lazy {
        val content = inflate(context, R.layout.custom_panel_content_layout, null)
        addView(content)

        return@lazy listOf<ImageView>(
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5)
        )
    }

    init {
        inflate(context, R.layout.custom_panel_layout, this)
    }

    fun initIcons(icons: List<Drawable>) {

        for (i in buttons.value.indices) {
            buttons.value[i].setImageDrawable(icons[i])
        }
    }
}