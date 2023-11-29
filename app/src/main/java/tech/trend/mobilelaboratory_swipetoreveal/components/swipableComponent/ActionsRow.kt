package tech.trend.mobilelaboratory_swipetoreveal.components.swipableComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import tech.trend.mobilelaboratory_swipetoreveal.R

@Composable
fun ActionsRow(
    modifier: Modifier = Modifier,
    onAction: () -> Unit = {},
) {
    Box(modifier = modifier
        .background(Color.Red)
        .width(100.dp)
        .fillMaxHeight()) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.twotone_delete_outline_24),
            contentDescription = "Delete",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}