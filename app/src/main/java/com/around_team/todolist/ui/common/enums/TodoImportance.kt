package com.around_team.todolist.ui.common.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.around_team.todolist.R
import com.around_team.todolist.ui.theme.JetTodoListTheme

/**
 * Enum representing the importance levels of Todo items.
 *
 * @property text Optional string resource ID representing textual description.
 * @property iconId Optional drawable resource ID representing icon for the importance level.
 */
enum class TodoImportance(
    override val text: Int? = null,
    override val iconId: Int? = null
): ITabs {
    Low(iconId = R.drawable.ic_low_priority),
    Basic(text = R.string.no),
    Important(iconId = R.drawable.ic_high_priority);

    companion object {
        fun getFromOrdinal(ordinal: Int): TodoImportance {
            return when (ordinal) {
                0 -> Low
                1 -> Basic
                2 -> Important
                else -> throw IllegalArgumentException(ordinal.toString())
            }
        }
    }
}

/**
 * Composable function to retrieve the color associated with the icon of a TodoImportance level.
 *
 * @return The color associated with the icon for this TodoImportance level.
 */
@Composable
fun TodoImportance.getIconColor(): Color {
    return when (this) {
        TodoImportance.Low -> JetTodoListTheme.colors.colors.gray
        TodoImportance.Basic -> Color.Transparent
        TodoImportance.Important -> JetTodoListTheme.colors.colors.red
    }
}