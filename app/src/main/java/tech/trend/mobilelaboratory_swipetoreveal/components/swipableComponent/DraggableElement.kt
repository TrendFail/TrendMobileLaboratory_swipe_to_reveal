package tech.trend.mobilelaboratory_swipetoreveal.components.swipableComponent


import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun DraggableElement(
    isRevealed: Boolean,
    actionsSize: MutableState<Float>,
    onExpand: () -> Unit,
    onCollapse: () -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {

    val _actionsSize by remember {actionsSize}

    var offsetX by remember { mutableStateOf(0f) }
    var cardElevation by remember { mutableStateOf(0.dp) }

    val coroutineScope = rememberCoroutineScope()

    val offsetAnimated = remember {
        Animatable(_actionsSize)
    }

    LaunchedEffect(key1 = isRevealed) {
        offsetAnimated.animateTo(
            when (isRevealed) {
                true -> -_actionsSize
                false -> 0f
            }
        )
    }
    LaunchedEffect(key1 = Unit) {
        launch {
            snapshotFlow { offsetAnimated.value }.collect {
                offsetX = it
            }
        }
        launch {
            snapshotFlow { offsetX }.collect {
                cardElevation = (0.._actionsSize.roundToInt()).convert(
                    abs(offsetX) ,
                    0..8
                ).dp
            }
        }

    }

    Box(
        modifier = Modifier
            .offset { IntOffset((offsetX).roundToInt(), 0) }
            .shadow(elevation = cardElevation, shape = RoundedCornerShape(cardElevation))
            .background(Color.White, RoundedCornerShape(cardElevation))
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onHorizontalDrag = { _, dragAmount ->
                        val newX = offsetX + dragAmount
                        offsetX = newX.coerceIn(-_actionsSize, 0f)
                    },
                    onDragEnd = {
                        if (offsetX < -(_actionsSize * 0.4f)) {
                            onExpand.invoke()
                            coroutineScope.launch {
                                offsetAnimated.snapTo(offsetX)
                                offsetAnimated.animateTo(-_actionsSize)
                            }
                        } else {
                            onCollapse()
                            coroutineScope.launch {
                                offsetAnimated.snapTo(offsetX)
                                offsetAnimated.animateTo(0f)
                            }
                        }
                    }
                )
            },
        content = content
    )
}

private fun IntRange.convert(currentValue:Float, targetRange:IntRange): Float {
    val ratio = currentValue / (this.last - this.first)
    return (ratio * (targetRange.last - targetRange.first))
}