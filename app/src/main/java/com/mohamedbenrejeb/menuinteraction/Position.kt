package com.mohamedbenrejeb.menuinteraction

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import kotlin.math.abs

enum class Position {
    Top, Right, Bottom, Left
}

fun Position.getOffset(
    buttonSizePx: Float
): Offset {
    return when (this) {
        Position.Top -> Offset(x = 0f, y = -buttonSizePx * 1.25f)
        Position.Right -> Offset(x = buttonSizePx * 1.25f, y = 0f)
        Position.Bottom -> Offset(x = 0f, y = buttonSizePx * 1.25f)
        Position.Left -> Offset(x = -buttonSizePx * 1.25f, y = 0f)
    }
}

fun getPosition(
    offset: Offset,
    buttonSizePx: Float,
): Position? {

    if (abs(offset.x) > abs(offset.y)) {
        if (offset.x > buttonSizePx) return Position.Right
        else if (-offset.x > buttonSizePx) return Position.Left
    }

    if (offset.y > buttonSizePx) return Position.Bottom
    else if (-offset.y > buttonSizePx) return Position.Top

    return null
}