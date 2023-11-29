package tech.trend.mobilelaboratory_swipetoreveal.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SomeContent(id: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = id,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .height(60.dp)
                .fillMaxSize(),
        )
        Divider(thickness = 1.dp, color = Color.LightGray)
    }
}