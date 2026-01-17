package com.skyapp.newsapp.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BackIconButton(
    color: Color = Color.Black,
    onClick: () -> Unit
) {

    IconButton(
        onClick = {
            onClick.invoke()
        },
        modifier = Modifier.size(46.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
            contentDescription = "App menu btn",
            tint = color,
            modifier = Modifier.size(23.dp)
        )
    }
}



@Composable
fun AppBtn(
    icon: ImageVector = Icons.Default.PersonOutline,
    color: Color = Color.White,
    onClick: () -> Unit
) {

    IconButton(
        onClick = {
            onClick.invoke()
        },
        modifier = Modifier.size(46.dp)
    ) {
        Icon(
            icon,
            contentDescription = "App menu btn",
            tint = color,
            modifier = Modifier.size(27.dp)
        )
    }
}

@Composable
fun MoreBtn(
    icon : ImageVector = Icons.Default.MoreVert,
    color : Color = Color.Black,
    onClick: () -> Unit
) {

    IconButton(
        onClick = {
            onClick.invoke()
        },
        modifier = Modifier.size(46.dp)
    ) {
        Icon(
            icon,
            contentDescription = "App menu btn",
            tint = color,
            modifier = Modifier.size(27.dp)
        )
    }
}


@Composable
fun MenuBarIconBtn(
    color: Color = Color.White,
    onClick: () -> Unit
) {

    IconButton(
        onClick = {
            onClick.invoke()
        },
        modifier = Modifier.size(46.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Sort,
            contentDescription = "App menu btn",
            tint = color,
            modifier = Modifier.size(23.dp)
        )
    }
}


@Composable
fun AppLabelHeader(
    name : String,

) {

    Box(
        modifier = Modifier
            .size(50.dp)
            .shadow(4.dp, shape = CircleShape)
            .drawBehind {
                val radius = size.minDimension / 2


                drawArc(
                    color = Color(0xFFE0E0E0),
                    startAngle = 90f,
                    sweepAngle = 180f,
                    useCenter = true,
                    size = size
                )


                drawArc(
                    color = Color.White,
                    startAngle = -90f,
                    sweepAngle = 180f,
                    useCenter = true,
                    size = size
                )
            }
            .clip(CircleShape)

    ) {
        Text(
            text = name.take(1).uppercase(),
            fontSize = 41.5.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }




}





@Composable
fun AppCardLabel(
    text: String,
    fontSize: TextUnit = 13.sp,
    backgroundColor: Color = Color.White,
    textColor: Color = contentColorFor(backgroundColor),
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(5.dp),
    icon: Boolean = false,
    iconFound: ImageVector = Icons.Default.Newspaper,
    appendIconFront: Boolean = false,
    onClick: () -> Unit
) {


    val image = if (icon) iconFound else null
    TextButton(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = shape,
        enabled = enabled,
        contentPadding = contentPadding,
        onClick = {
            onClick.invoke()
        }
    ) {
        if (appendIconFront) {

            if (icon) {
                image?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "",
                        modifier = Modifier.padding(horizontal = 5.dp).size(15.dp),
                        tint = contentColorFor(backgroundColor),

                    )
                }
            }
        }

        Text(
            text = text,
            fontSize = fontSize,
            color = textColor,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Monospace,
        )

        if (!appendIconFront) {
            if (icon) {
                image?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "",
                        modifier = Modifier.padding(horizontal = 5.dp),
                        tint = Color.Black
                    )
                }
            }
        }
    }
}
