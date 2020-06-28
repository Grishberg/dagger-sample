package com.github.grishberg.daggersample.custompanel.stub

import com.github.grishberg.daggersample.custompanel.common.DimensionProvider
import javax.inject.Inject

class CustomPanelStubDrawable @Inject constructor(
    private val dimension: DimensionProvider
) {
}