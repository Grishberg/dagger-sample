package com.github.grishberg.daggersample.custompanel.panel

import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import com.github.grishberg.daggersample.R
import com.github.grishberg.daggersample.custompanel.common.DimensionProvider
import com.github.grishberg.daggersample.custompanel.stub.CACHED_FILE_NAME
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

private const val TAG = "CustomPanelView"

/**
 * heavy view
 */
class CustomPanelView @Inject constructor(
    private val activity: Activity,
    private val dimensions: DimensionProvider
) : FrameLayout(activity) {
    private var pendingMeasureAction: () -> Unit = {}

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

        pendingMeasureAction = { saveToImage() }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        pendingMeasureAction.invoke()
    }

    private fun saveToImage() {
        val bitmap = Bitmap.createBitmap(
            measuredWidth,
            measuredHeight,
            Bitmap.Config.RGB_565
        )
        val c = Canvas(bitmap)
        draw(c)

        try {
            //save bitmap to sdcard
            var out: FileOutputStream? = null
            try {
                val file = File(activity.cacheDir, CACHED_FILE_NAME)

                out = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)
            } catch (e: Exception) {
                Log.e(TAG, "save image error", e)
            } finally {
                //close output stream (important!)
                try {
                    out?.close()
                } catch (ignore: Throwable) {
                }
            }
        } catch (ne: PackageManager.NameNotFoundException) {
        }

    }
}