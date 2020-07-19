package com.github.grishberg.daggersample.custompanel.common

import android.app.Activity
import com.github.grishberg.daggersample.R
import com.github.grishberg.daggersample.custompanel.di.FirstFrameScope
import javax.inject.Inject

@FirstFrameScope
class DimensionProvider @Inject constructor(activity: Activity) {
    val iconSize = activity.resources.getDimensionPixelSize(R.dimen.panel_icons_size)
    val textSize = activity.resources.getDimensionPixelSize(R.dimen.panel_text_size)
    val textTopPadding = activity.resources.getDimensionPixelSize(R.dimen.panel_text_top_padding)
    val stubCornerRadius = activity.resources.getDimension(R.dimen.panel_stub_corner_radius)
}