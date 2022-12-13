package com.mohamedbenrejeb.menuinteraction.shapes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.mohamedbenrejeb.menuinteraction.*
import com.mohamedbenrejeb.menuinteraction.ui.theme.PsBlue
import com.mohamedbenrejeb.menuinteraction.ui.theme.PsGreen
import com.mohamedbenrejeb.menuinteraction.ui.theme.PsRed
import com.mohamedbenrejeb.menuinteraction.ui.theme.PsPink

@Composable
fun DrawShape(
    position: Position,
    isSelected: Boolean,
) {
    val paint = remember {
        Paint().apply {
            style = PaintingStyle.Stroke
            strokeWidth = 30f
        }
    }

    val frameworkPaint = remember {
        paint.asFrameworkPaint()
    }

    when (position) {
        Position.Top -> {
            val color = PsGreen

            Canvas(modifier = Modifier
                .fillMaxSize()
                .padding(start = 14.dp, end = 14.dp, top = 12.dp, bottom = 16.dp)) {
                if (isSelected) {
                    drawGlowTriangle(
                        color = color,
                        frameworkPaint = frameworkPaint,
                        paint = paint
                    )
                } else {
                    drawPoints(
                        points = listOf(
                            Offset(size.width / 2, 0f),
                            Offset(0f, size.height),
                            Offset(size.width, size.height),
                            Offset(size.width / 2, 0f),
                        ),
                        color = Color.LightGray,
                        pointMode = PointMode.Polygon,
                        strokeWidth = 4.dp.toPx()
                    )
                }
            }
        }
        Position.Right -> {
            val color = PsRed

            Canvas(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)) {
                if (isSelected) {
                    drawGlowCircle(
                        color = color,
                        frameworkPaint = frameworkPaint,
                        paint = paint
                    )
                } else {
                    drawCircle(
                        Color.LightGray,
                        style = Stroke(width = 4.dp.toPx())
                    )
                }
            }
        }
        Position.Bottom -> {
            val color = PsBlue

            Canvas(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                if (isSelected) {
                    drawGlowLine(
                        color = color,
                        frameworkPaint = frameworkPaint,
                        paint = paint,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, size.height),
                    )

                    drawGlowLine(
                        color = color,
                        frameworkPaint = frameworkPaint,
                        paint = paint,
                        start = Offset(size.width, 0f),
                        end = Offset(0f, size.height),
                    )
                } else {
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, size.height),
                        strokeWidth = 4.dp.toPx()
                    )

                    drawLine(
                        color = Color.LightGray,
                        start = Offset(size.width, 0f),
                        end = Offset(0f, size.height),
                        strokeWidth = 4.dp.toPx()
                    )
                }
            }
        }
        Position.Left -> {
            val color = PsPink

            Canvas(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                if (isSelected) {
                    drawGlowRect(
                        color = color,
                        frameworkPaint = frameworkPaint,
                        paint = paint
                    )
                } else {
                    drawRect(
                        Color.LightGray,
                        style = Stroke(width = 4.dp.toPx())
                    )
                }

            }
        }
    }
}