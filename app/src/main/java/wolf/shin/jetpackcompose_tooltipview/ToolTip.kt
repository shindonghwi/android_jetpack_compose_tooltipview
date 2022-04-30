package wolf.shin.jetpackcompose_tooltipview

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

@Composable
fun ToolTip(
    modifier: Modifier = Modifier,
    component: ToolTipComponent
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        ConstraintLayout(constraintSet = ConstraintSet {
            val triangle = createRefFor("triangle")
            val content = createRefFor("content")

            // 삼각형 배치
            constrain(triangle) {
                when (component.direction) {
                    ToolTipDirection.CENTER_TOP -> {
                        bottom.linkTo(content.top)
                        start.linkTo(content.start)
                        end.linkTo(content.end)
                    }
                    ToolTipDirection.CENTER_BOTTOM -> {
                        top.linkTo(content.bottom)
                        start.linkTo(content.start)
                        end.linkTo(content.end)
                    }
                    ToolTipDirection.RIGHT_TOP -> {
                        bottom.linkTo(content.top)
                        end.linkTo(content.end)
                    }
                    ToolTipDirection.RIGHT_BOTTOM -> {
                        top.linkTo(content.bottom)
                        end.linkTo(content.end)
                    }
                    ToolTipDirection.LEFT_TOP -> {
                        bottom.linkTo(content.top)
                        start.linkTo(content.start)
                    }
                    ToolTipDirection.LEFT_BOTTOM -> {
                        top.linkTo(content.bottom)
                        start.linkTo(content.start)
                    }
                    ToolTipDirection.RIGHT_CENTER -> {
                        top.linkTo(content.top)
                        bottom.linkTo(content.bottom)
                        start.linkTo(content.end)
                    }
                    ToolTipDirection.LEFT_CENTER -> {
                        top.linkTo(content.top)
                        bottom.linkTo(content.bottom)
                        end.linkTo(content.start)
                    }
                }
            }

        }) {
            ContentView(modifier = Modifier.layoutId("content"), component = component)
            TriangleView(modifier = Modifier.layoutId("triangle"), component = component)
        }
    }
}

@Composable
private fun TriangleView(modifier: Modifier, component: ToolTipComponent) {
    Canvas(
        modifier = modifier.then(
            Modifier
                .padding(
                    horizontal = when (component.direction) {
                        ToolTipDirection.LEFT_CENTER,
                        ToolTipDirection.RIGHT_CENTER -> 0.dp
                        else -> 14.dp
                    }
                )
                .size(
                    width = when (component.direction) {
                        ToolTipDirection.LEFT_CENTER,
                        ToolTipDirection.RIGHT_CENTER -> 8.dp
                        else -> 12.dp
                    },
                    height = when (component.direction) {
                        ToolTipDirection.LEFT_CENTER,
                        ToolTipDirection.RIGHT_CENTER -> 12.dp
                        else -> 8.dp
                    }
                )
        )
    ) {
        drawIntoCanvas { canvas ->
            canvas.drawOutline(
                outline = Outline.Generic(path = getTriangleEdge(size, component.direction)),
                paint = Paint().apply {
                    color = component.backgroundColor
                    pathEffect = PathEffect.cornerPathEffect(Rect(Offset.Zero, size).maxDimension / 5)
                }
            )
        }
    }
}

@Composable
private fun ContentView(modifier: Modifier, component: ToolTipComponent) {
    ConstraintLayout(
        modifier = modifier.then(
            Modifier
                .background(
                    color = component.backgroundColor,
                    shape = RoundedCornerShape(3.dp)
                )
        ),
        constraintSet = ConstraintSet {
            val title = createRefFor("title")
            val content = createRefFor("content")
            val button = createRefFor("button")

            if (component.style == ToolTipStyle.TITLE) {
                constrain(title) {
                    top.linkTo(parent.top, margin = 12.dp)
                    linkTo(
                        start = parent.start,
                        end = button.start,
                        startMargin = 12.dp,
                        endMargin = if (component.icon != null) 8.dp else 12.dp,
                        bias = 0f
                    )
                }
                constrain(content) {
                    top.linkTo(title.bottom, margin = 2.dp)
                    linkTo(
                        start = parent.start,
                        end = button.start,
                        startMargin = 12.dp,
                        endMargin = if (component.icon != null) 8.dp else 12.dp,
                        bias = 0f
                    )
                    bottom.linkTo(parent.bottom, margin = 12.dp)
                }

                constrain(button) {
                    top.linkTo(parent.top, margin = 12.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                }

            } else {
                constrain(content) {
                    top.linkTo(parent.top, margin = 12.dp)
                    bottom.linkTo(parent.bottom, margin = 12.dp)

                    linkTo(
                        start = parent.start,
                        end = if (component.icon != null) button.start else parent.end,
                        startMargin = 12.dp,
                        endMargin = if (component.icon != null) 8.dp else 12.dp,
                        bias = 0f
                    )
                }
                component.icon?.let {
                    constrain(button) {
                        top.linkTo(parent.top, margin = 12.dp)
                        end.linkTo(parent.end, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 12.dp)
                    }
                }
            }

        }
    ) {
        if (component.style == ToolTipStyle.TITLE) {
            /** 제목 */
            component.title?.let {
                Text(
                    text = it.text,
                    modifier = Modifier.layoutId("title"),
                    color = it.textColor,
                    textAlign = TextAlign.Start,
                    style = component.title.textStyle
                )
            }
        }


        /** 내용 */
        Text(
            text = component.content.text,
            modifier = Modifier.layoutId("content"),
            color = component.content.textColor,
            style = component.content.textStyle
        )

        /** 아이콘 */
        component.icon?.let { toolTipIcon ->
            Icon(
                modifier = Modifier
                    .layoutId("button")
                    .clickable(interactionSource = MutableInteractionSource(), indication = null) { toolTipIcon.event?.let { it() } }
                    .size(
                        width = 16.dp,
                        height = if (component.style == ToolTipStyle.TITLE) {
                            38.dp
                        } else {
                            20.dp
                        }
                    )
                    .padding(
                        bottom = if (component.style == ToolTipStyle.TITLE) {
                            22.dp
                        } else {
                            4.dp
                        }
                    ),
                tint = toolTipIcon.tint,
                painter = painterResource(id = toolTipIcon.img),
                contentDescription = "icon"
            )
        }
    }
}


private fun getTriangleEdge(size: Size, direction: ToolTipDirection): Path {
    return Path().apply {
        when (direction) {

            ToolTipDirection.CENTER_TOP,
            ToolTipDirection.RIGHT_TOP,
            ToolTipDirection.LEFT_TOP -> {
                moveTo(0f, size.height)
                lineTo(size.width / 2, 0f)
                lineTo(size.width, size.height)
            }

            ToolTipDirection.CENTER_BOTTOM,
            ToolTipDirection.RIGHT_BOTTOM,
            ToolTipDirection.LEFT_BOTTOM -> {
                moveTo(0f, 0f)
                lineTo(size.width / 2, size.height)
                lineTo(size.width, 0f)
            }

            ToolTipDirection.RIGHT_CENTER -> {
                moveTo(0f, 0f)
                lineTo(size.width, size.height / 2)
                lineTo(0f, size.height)
            }
            ToolTipDirection.LEFT_CENTER -> {
                moveTo(size.width, 0f)
                lineTo(0f, size.height / 2)
                lineTo(size.width, size.height)
            }
        }
    }
}