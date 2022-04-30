package wolf.shin.jetpackcompose_tooltipview

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import org.jetbrains.annotations.NotNull

data class ToolTipComponent(
    val style: ToolTipStyle,
    val direction: ToolTipDirection,
    val backgroundColor: Color,
    val title: ToolTipText? = null,
    val content: ToolTipText,
    val icon: ToolTipIcon? = null
)

fun getToolTipComponent(
    style: ToolTipStyle,
    direction: ToolTipDirection,
    backgroundColor: Color,
    title: ToolTipText? = null,
    content: ToolTipText,
    icon: ToolTipIcon? = null // 아이콘 drawable
): ToolTipComponent {
    return ToolTipComponent(
        style = style,
        direction = direction,
        backgroundColor = backgroundColor,
        title = title,
        content = content,
        icon = icon
    )
}

enum class ToolTipStyle(val style: Int) {
    DEFAULT(0),
    TITLE(1),
}

data class ToolTipText(
    val text: String,
    val textColor: Color = Color.White,
    @NotNull val textStyle: TextStyle
)

enum class ToolTipDirection {
    CENTER_TOP,
    CENTER_BOTTOM,
    RIGHT_TOP,
    RIGHT_BOTTOM,
    LEFT_TOP,
    LEFT_BOTTOM,
    RIGHT_CENTER,
    LEFT_CENTER,
}

data class ToolTipIcon(
    val img: Int,
    val tint: Color,
    val event: (() -> Unit)?
)