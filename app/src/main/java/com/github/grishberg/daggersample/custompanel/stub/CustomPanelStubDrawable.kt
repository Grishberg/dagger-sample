package com.github.grishberg.daggersample.custompanel.stub

import android.graphics.*
import android.graphics.drawable.Drawable
import com.github.grishberg.daggersample.custompanel.common.DimensionProvider
import javax.inject.Inject

class CustomPanelStubDrawable @Inject constructor(
    dimension: DimensionProvider
) : Drawable() {
    private val bgPaint = Paint()
    private val radius: Float = dimension.stubCornerRadius
    val boundHeight = dimension.iconSize + dimension.textSize + dimension.textTopPadding

    init {
        bgPaint.color = Color.GREEN
    }

    override fun onBoundsChange(bounds: Rect) {
        bounds.bottom = boundHeight
        super.onBoundsChange(bounds)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRoundRect(
            bounds.left.toFloat(),
            bounds.top.toFloat(),
            bounds.right.toFloat(),
            bounds.bottom.toFloat(),
            radius,
            radius,
            bgPaint
        )
    }

    override fun setAlpha(alpha: Int) = Unit

    override fun getOpacity(): Int = PixelFormat.OPAQUE

    override fun setColorFilter(colorFilter: ColorFilter?) = Unit
}