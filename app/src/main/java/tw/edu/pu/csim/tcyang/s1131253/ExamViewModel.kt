// ExamViewModel.kt
package tw.edu.pu.csim.tcyang.s1131253

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ExamViewModel : ViewModel() {
    private val _score = mutableStateOf(0)
    val score: State<Int> = _score

    fun setScore(newScore: Int) {
        _score.value = newScore
    }
}