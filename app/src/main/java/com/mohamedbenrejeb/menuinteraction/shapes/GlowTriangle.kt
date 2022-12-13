package com.mohamedbenrejeb.menuinteraction.shapes

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.dp

fun DrawScope.drawGlowTriangle(
    color: Color,
    frameworkPaint: NativePaint,
    paint: Paint,
) {
    val points = listOf(
        Offset(size.width / 2, 0f),
        Offset(0f, size.height),
        Offset(size.width, size.height),
        Offset(size.width / 2, 0f),
    )

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

        it.drawPoints(
            points = points,
            pointMode = PointMode.Polygon,
            paint = paint
        )

        drawPoints(
            points = points,
            color = Color.White,
            pointMode = PointMode.Polygon,
            strokeWidth = 4.dp.toPx()
        )

        frameworkPaint.setShadowLayer(
            30f,
            0f,
            0f,
            color
                .copy(alpha = .5f)
                .toArgb()
        )

        it.drawPoints(
            points = points,
            pointMode = PointMode.Polygon,
            paint = paint
        )

        drawPoints(
            points = points,
            color = Color.White,
            pointMode = PointMode.Polygon,
            strokeWidth = 4.dp.toPx()
        )
    }
}