package com.around_team.todolist.ui.screens.edit.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.around_team.todolist.ui.theme.JetTodoListTheme
import com.around_team.todolist.ui.theme.TodoListTheme

/**
 * A composable function that displays a custom date picker using Jetpack Compose.
 *
 * @param state The state object that holds the date picker's state.
 * @param modifier Optional modifier for styling or positioning the date picker.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(state: DatePickerState, modifier: Modifier = Modifier) {
    DatePicker(
        modifier = modifier,
        state = state,
        showModeToggle = false,
        title = { },
        headline = { },
        colors = DatePickerDefaults.colors(
            containerColor = JetTodoListTheme.colors.back.secondary,
            headlineContentColor = JetTodoListTheme.colors.label.primary,
            weekdayContentColor = JetTodoListTheme.colors.label.tertiary,
            yearContentColor = JetTodoListTheme.colors.label.primary,
            currentYearContentColor = JetTodoListTheme.colors.label.primary,
            selectedYearContentColor = JetTodoListTheme.colors.colors.blue,
            selectedYearContainerColor = JetTodoListTheme.colors.colors.blue.copy(0.1F),
            dayContentColor = JetTodoListTheme.colors.label.primary,
            selectedDayContentColor = JetTodoListTheme.colors.colors.blue,
            selectedDayContainerColor = JetTodoListTheme.colors.colors.blue.copy(0.1F),
            todayContentColor = JetTodoListTheme.colors.label.primary,
            todayDateBorderColor = Color.Transparent
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun CustomDatePickerPreview() {
    val state = rememberDatePickerState()
    TodoListTheme {
        Box(modifier = Modifier.background(JetTodoListTheme.colors.back.primary)) {
            CustomDatePicker(state)
        }
    }
}