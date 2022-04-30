package wolf.shin.jetpackcompose_tooltipview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import wolf.shin.jetpackcompose_tooltipview.ui.theme.JetpackCompose_ToolTipViewTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCompose_ToolTipViewTheme() {

                val directionList = listOf(
                    ToolTipDirection.CENTER_TOP,
                    ToolTipDirection.CENTER_BOTTOM,
                    ToolTipDirection.LEFT_BOTTOM,
                    ToolTipDirection.LEFT_CENTER,
                    ToolTipDirection.LEFT_TOP,
                    ToolTipDirection.RIGHT_BOTTOM,
                    ToolTipDirection.RIGHT_CENTER,
                    ToolTipDirection.RIGHT_TOP,
                )

                Column(modifier = Modifier.fillMaxSize()) {

                    directionList.forEachIndexed { index, direction ->
                        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {

                            ToolTip(
                                component = getToolTipComponent(
                                    style = ToolTipStyle.TITLE,
                                    direction = direction,
                                    backgroundColor = Color.Black,
                                    title = ToolTipText("Title${index}", Color.White, MaterialTheme.typography.subtitle1),
                                    content = ToolTipText("Content${index}", Color.White, MaterialTheme.typography.body2),
                                    icon = ToolTipIcon(img = R.drawable.ic_info, tint = Color.White) { Toast.makeText(this@MainActivity, "asdasd", Toast.LENGTH_LONG).show() }
                                )
                            )

                            ToolTip(
                                component = getToolTipComponent(
                                    style = ToolTipStyle.TITLE,
                                    direction = direction,
                                    backgroundColor = Color.Black,
                                    title = ToolTipText("Title${index}", Color.White, MaterialTheme.typography.subtitle1),
                                    content = ToolTipText("Content${index}", Color.White, MaterialTheme.typography.body2),
                                    icon = ToolTipIcon(img = R.drawable.ic_info, tint = Color.White) { Toast.makeText(this@MainActivity, "asdasd", Toast.LENGTH_LONG).show() }
                                )
                            )
                        }
                    }
                }

            }
        }
    }
}

// 랜덤 칼라 가져오기
fun Color.Companion.random(): Color {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    return Color(red, green, blue)
}