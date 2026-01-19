package com.skyapp.newsapp.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Outbox
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

enum class DropDownAction {
    BOOKMARK,
    SHARE
}
@Composable
fun AppSimpleDropdown(
    items : List<String> = listOf(),
    expanded: Boolean,
    onDismiss: () -> Unit,
    onActionSelected: (DropDownAction) -> Unit,
    modifier: Modifier = Modifier
) {
//    val items = listOf(
//        "BookMark",
//        "Share"
//    )


    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .widthIn(min = 160.dp)
    ) {
        items.forEach { item ->
            DropdownMenuItem(
                text = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector =mapIconBasedOnName(item) ,
                            contentDescription = item,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(item)
                    }
                },
                onClick = {
                    val dropDownAction = if(item == "BookMark") DropDownAction.BOOKMARK else DropDownAction.SHARE // is not fully reusable just for simple completion ,as time is less
                    onActionSelected(dropDownAction)
                    onDismiss()
                }
            )
        }
    }
}


fun mapIconBasedOnName(name: String) : ImageVector {
    return when(name) {
        "BookMark" -> Icons.Default.Bookmark
        "Share" -> Icons.Default.Share
        else -> Icons.Default.Outbox
    }
}