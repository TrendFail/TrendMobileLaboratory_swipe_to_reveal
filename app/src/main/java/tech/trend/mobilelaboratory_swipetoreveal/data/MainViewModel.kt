package tech.trend.mobilelaboratory_swipetoreveal.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {

    var revealId:String? by mutableStateOf(null)

    fun generateList():List<String> = listOf(
        "line 1",
        "line 2",
        "line 3",
        "line 4",
        "line 5",
        "line 6",
        "line 7",
        "line 8",
        "line 9",
        "line 10",
        )

}