package com.mohamedbenrejeb.menuinteraction.shapes

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.NativePaint
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp

fun DrawScope.drawGlowRect(
    color: Color,
    frameworkPaint: NativePaint,
    paint: Paint
) {
    this.drawIntoCanvas {
        val transparent = color
            .copy(alpha = 0f)
            .toArgb()

        frameworkPaint.color = transparent

        frameworkPaint.setShadowLayer(
            10f,
            0f,
            0f,
            color
                .copy(alpha = .5f)
                .toArgb()
        )

        it.drawRect(
            left = 0f,
            top = 0f,
            right = size.width,
            bottom = size.height,
            paint = paint
        )

        drawRect(
            Color.White,
            style = Stroke(width = 4.dp.toPx())
        )


        frameworkPaint.setShadowLayer(
            30f,
            0f,
            0f,
            color
                .copy(alpha = .5f)
                .toArgb()
        )


        it.drawRect(
            left = 0f,
            top = 0f,
            right = size.width,
            bottom = size.height,
            paint = paint
        )

        drawRect(
            Color.White,
            style = Stroke(width = 4.dp.toPx())
        )
    }
}