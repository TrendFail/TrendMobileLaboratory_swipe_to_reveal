package tech.trend.mobilelaboratory_swipetoreveal.components.swipableComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import tech.trend.mobilelaboratory_swipetoreveal.components.SomeContent

@Composable
fun ItemElement(
    id: String,
    isRevealed:Boolean,
    onExpand:() -> Unit,
    onCollapse:() -> Unit,

) {
    val actionsSize = remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(Color.Gray)

    ) {
        ActionsRow(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .onSizeChanged {
                    actionsSize.value = it.width.toFloat()
                },
            onAction = {}
        )
        DraggableElement(
            isRevealed = isRevealed,
            actionsSize = actionsSize,
            onExpand = onExpand,
            onCollapse = onCollapse,
        ) {
            SomeContent(id = id)

        }
    }
}