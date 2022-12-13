package com.mohamedbenrejeb.menuinteraction

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.mohamedbenrejeb.menuinteraction.shapes.DrawShape
import com.mohamedbenrejeb.menuinteraction.ui.theme.MenuInteractionTheme
import com.mohamedbenrejeb.menuinteraction.ui.theme.PsBlue
import kotlinx.coroutines.launch
import kotlin.math.*

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen() {
    val scope = rememberCoroutineScope()
    val buttonSize = 90.dp

    // Swipe size in px
    val buttonSizePx = with(LocalDensity.current) { buttonSize.toPx() }
    val dragSizePx = buttonSizePx * 1.5f

    // Drag offset
    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }
    var pressed by remember { mutableStateOf(false) }
    var gesture by remember { mutableStateOf("") }

    // Vertical Swipe
//    val verticalSwipeableState = rememberSwipeableState(0)
//    val verticalAnchors = mapOf(0f to 0, -swipeSizePx to 1, swipeSizePx to 2)
    // Height changes depending on swipe
//    val additionalHeightPx = offsetY.value
//    val additionalHeight = with(LocalDensity.current) {
//        (abs(additionalHeightPx).roundToInt()).toDp()
//    }
//    val height = buttonSize + additionalHeight

    // Horizontal Swipe
//    val horizontalSwipeableState = rememberSwipeableState(0)
//    val horizontalAnchors = mapOf(0f to 0, -swipeSizePx to 1, swipeSizePx to 2)
    // Width changes depending on swipe
//    val additionalWidthPx = offsetX.value
//    val additionalWidth = with(LocalDensity.current) {
//        (abs(additionalWidthPx).roundToInt()).toDp()
//    }
//    val width = buttonSize + additionalWidth
//
//    var selectedBox by remember { mutableStateOf("") }

    var isDragging by remember { mutableStateOf(false) }

    var currentPosition by remember { mutableStateOf<Position?>(null) }

    LaunchedEffect(offsetX.value, offsetY.value) {

        val newPosition = getPosition(
            offset = Offset(offsetX.value, offsetY.value),
            buttonSizePx = buttonSizePx
        )

        gesture = "new pos $newPosition "

        currentPosition = newPosition
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.size(buttonSize * 2)) {
                currentPosition?.let { position ->
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .fillMaxSize()
                            .background(Color.DarkGray.copy(alpha = 0.5f))
                            .padding(20.dp)
                        ,
                        contentAlignment = Alignment.Center
                    ) {
                        DrawShape(
                            position,
                            isSelected = true
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .size(buttonSize * 4)
                    .background(Color.White, CircleShape)
                ,
                contentAlignment = Alignment.Center
            ) {

                Box(
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                x = (offsetX.value).roundToInt(),
                                y = (offsetY.value).roundToInt()
                            )
                        }
                        .width(buttonSize)
                        .height(buttonSize)
                        .alpha(0.8f)
                        .background(Color.DarkGray, CircleShape)
                        .pointerInput(Unit) {

                            detectDragGestures(
                                onDragStart = {
                                    isDragging = true
                                },
                                onDragEnd = {
                                    scope.launch {
                                        offsetX.animateTo(0f)
                                    }
                                    scope.launch {
                                        offsetY.animateTo(0f)
                                    }
                                    isDragging = false
                                },
                                onDragCancel = {
                                    scope.launch {
                                        offsetX.animateTo(0f)
                                    }
                                    scope.launch {
                                        offsetY.animateTo(0f)
                                    }
                                    isDragging = false
                                },
                                onDrag = { change, dragAmount ->
                                    change.consume()

                                    scope.launch {
                                        val newOffsetX = offsetX.value + dragAmount.x
                                        val newOffsetY = offsetY.value + dragAmount.y

                                        if (
                                            sqrt(newOffsetX.pow(2) + newOffsetY.pow(2)) < dragSizePx
                                        ) {
                                            offsetX.snapTo(newOffsetX)
                                            offsetY.snapTo(newOffsetY)
                                        } else if (
                                            sqrt(offsetX.value.pow(2) + newOffsetY.pow(2)) < dragSizePx
                                        ) {
                                            offsetY.snapTo(newOffsetY)
                                        } else if (
                                            sqrt(newOffsetX.pow(2) + offsetY.value.pow(2)) < dragSizePx
                                        ) {
                                            offsetX.snapTo(newOffsetX)
                                        }
                                    }

                                }
                            )
                        }

                )

                val buttonAlpha = remember {
                    Animatable(0f)
                }

                LaunchedEffect(key1 = isDragging) {
                    if (isDragging) {
                        buttonAlpha.animateTo(1f)
                    } else {
                        buttonAlpha.animateTo(0f)
                    }
                }

                Position.values().forEach { position ->
                    val offset = position.getOffset(buttonSizePx)
                    MyButton(
                        modifier = Modifier
                            .offset {
                                IntOffset(
                                    x = offset.x.roundToInt(),
                                    y = offset.y.roundToInt()
                                )
                            }
                            .graphicsLayer {
                                alpha = buttonAlpha.value
                                scaleX = buttonAlpha.value
                                scaleY = buttonAlpha.value
                            }
                            .size(buttonSize)
                            .padding(8.dp)
                        ,
                        isSelected = position == currentPosition,
                        position = position
                    )
                }

            }
        }


    }

}

@Composable
fun MyButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    position: Position,
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .fillMaxSize()
            .background(Color.DarkGray.copy(alpha = 0.5f))
        ,
        contentAlignment = Alignment.Center
    ) {
        DrawShape(
            position,
            isSelected = isSelected
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MenuInteractionTheme {
        HomeScreen()
    }
}