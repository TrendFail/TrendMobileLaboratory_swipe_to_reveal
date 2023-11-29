package tech.trend.mobilelaboratory_swipetoreveal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import tech.trend.mobilelaboratory_swipetoreveal.components.swipableComponent.ItemElement
import tech.trend.mobilelaboratory_swipetoreveal.data.MainViewModel
import tech.trend.mobilelaboratory_swipetoreveal.ui.theme.MobileLaboratorySwipeToRevealTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileLaboratorySwipeToRevealTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: MainViewModel = MainViewModel()) {
    LazyColumn {
        items(viewModel.generateList()) {
            ItemElement(
                id = it,
                isRevealed = viewModel.revealId == it,
                onExpand = {viewModel.revealId = it},
                onCollapse = {
                    if (viewModel.revealId == it) {
                        viewModel.revealId = null
                    }
                }
            )
        }
    }
}


