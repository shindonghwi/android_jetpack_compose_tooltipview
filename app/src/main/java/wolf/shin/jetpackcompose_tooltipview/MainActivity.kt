package wolf.shin.jetpackcompose_tooltipview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import wolf.shin.jetpackcompose_tooltipview.ui.theme.JetpackCompose_ToolTipViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCompose_ToolTipViewTheme {

                Column(modifier = Modifier.fillMaxSize()) {

                    ToolTip(
                        component = getToolTipComponent(
                            style = ToolTipStyle.TITLE,
                            direction = ToolTipDirection.CENTER_BOTTOM,
                            backgroundColor = Color.Black,
                            title = ToolTipText("I'm ToolTip Title", Color.White, MaterialTheme.typography.subtitle1),
                            content = ToolTipText("I'm ToolTip Content", Color.White, MaterialTheme.typography.body2),
                            icon = Triple(R.drawable.ic_info, Color.White) {}
                        )
                    )

                }

            }
        }
    }
}