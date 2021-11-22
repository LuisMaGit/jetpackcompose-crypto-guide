import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

abstract class SpacerV {
    companion object {
        @Composable
        fun Small() {
            Custom(5)
        }

        @Composable
        fun Medium() {
            Custom(10)
        }

        @Composable
        fun Big() {
            Custom(15)
        }

        @Composable
        fun Bigger() {
            Custom(25)
        }

        @Composable
        fun Custom(value: Int) {
            Spacer(modifier = Modifier.height(value.dp))
        }
    }
}

abstract class SpacerH {
    companion object {
        @Composable
        fun Small() {
            Custom(5)
        }

        @Composable
        fun Medium() {
            Custom(10)
        }

        @Composable
        fun Big() {
            Custom(15)
        }

        @Composable
        fun Bigger() {
            Custom(25)
        }

        @Composable
        fun Custom(value: Int) {
            Spacer(modifier = Modifier.width(value.dp))
        }
    }
}