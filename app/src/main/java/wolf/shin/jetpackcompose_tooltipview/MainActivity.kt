package wolf.shin.jetpackcompose_tooltipview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import wolf.shin.jetpackcompose_tooltipview.ui.theme.JetpackCompose_ToolTipViewTheme
import wolf.shin.tooltip.*
import java.security.SecureRandom
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

                Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    ToolTip(
                        modifier = Modifier.padding(top = 6.dp),
                        component = getToolTipComponent(
                            style = ToolTipStyle.TITLE,
                            direction = ToolTipDirection.CENTER_BOTTOM,
                            backgroundColor = Color.Black,
                            title = ToolTipText("안녕하세요 Android 개발자 신동휘입니다 :)", Color.White, MaterialTheme.typography.subtitle1),
                            content = ToolTipText("\n\n" +
                                    "Phone: 010-8940-6835\n" +
                                    "email: shindonghwi8940@gmail.com\n" +
                                    "blog: https://medium.com/@wolf-android-developer\n" +
                                    "github: https://github.com/shindonghwi", Color.White, MaterialTheme.typography.body2),
                            icon = if (SecureRandom().nextInt(2) == 0) ToolTipIcon(img = R.drawable.ic_info, tint = Color.White) {
                                Toast.makeText(this@MainActivity, "Icon Click", Toast.LENGTH_LONG).show()
                            } else null,
                            cornerRoundValue = 20.dp
                        )
                    )

                    directionList.forEachIndexed { index, direction ->

                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {

                            ToolTip(
                                component = getToolTipComponent(
                                    style = if (SecureRandom().nextInt(2) == 0) ToolTipStyle.TITLE else ToolTipStyle.DEFAULT,
                                    direction = direction,
                                    backgroundColor = Color.random(),
                                    title = ToolTipText("Title${index}", Color.White, MaterialTheme.typography.subtitle1),
                                    content = ToolTipText("Content${index}", Color.White, MaterialTheme.typography.body2),
                                    icon = if (SecureRandom().nextInt(2) == 0) ToolTipIcon(img = R.drawable.ic_info, tint = Color.White) {
                                        Toast.makeText(this@MainActivity, "Icon Click", Toast.LENGTH_LONG).show()
                                    } else null
                                )
                            )

                            ToolTip(
                                component = getToolTipComponent(
                                    style = if (SecureRandom().nextInt(2) == 0) ToolTipStyle.TITLE else ToolTipStyle.DEFAULT,
                                    direction = direction,
                                    backgroundColor = Color.random(),
                                    title = ToolTipText("Title${index}", Color.White, MaterialTheme.typography.subtitle1),
                                    content = ToolTipText("Content${index}", Color.White, MaterialTheme.typography.body2),
                                    icon = if (SecureRandom().nextInt(2) == 0) ToolTipIcon(img = R.drawable.ic_info, tint = Color.White) {
                                        Toast.makeText(this@MainActivity, "Icon Click", Toast.LENGTH_LONG).show()
                                    } else null
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