package com.around_team.todolist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.around_team.todolist.ui.screens.edit.EditScreen
import com.around_team.todolist.ui.screens.edit.EditViewModel
import com.around_team.todolist.ui.screens.todos.TodosScreen
import com.around_team.todolist.ui.screens.todos.TodosViewModel

class NavGraph(
    private val navController: NavHostController,
) {
    @Composable
    fun Create() {
        val todosViewModel = hiltViewModel<TodosViewModel>()
        val editViewModel = hiltViewModel<EditViewModel>()

        NavHost(
            navController = navController,
            startDestination = Screens.TodosScreen.name,
            contentAlignment = Alignment.TopStart,
        ) {
            composable(
                route = "${Screens.TodosScreen.name}?$UPDATE_LIST_KEY={$UPDATE_LIST_KEY}",
                arguments = listOf(
                    navArgument(UPDATE_LIST_KEY) {
                        type = NavType.BoolType
                        defaultValue = false
                    },
                )
            ) { CreateTodosScreen(todosViewModel) }
            composable(
                route = "${Screens.EditScreen.name}?$TO_EDIT_TODO_ID_KEY={$TO_EDIT_TODO_ID_KEY}",
                arguments = listOf(
                    navArgument(TO_EDIT_TODO_ID_KEY) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                )
            ) { CreateEditScreen(editViewModel) }
        }
    }

    @Composable
    private fun CreateTodosScreen(viewModel: TodosViewModel) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val arguments = navBackStackEntry?.arguments
        val updateList = arguments?.getBoolean(TO_EDIT_TODO_ID_KEY, false) ?: true

        TodosScreen(
            viewModel = viewModel,
            toEditScreen = {
                if (it == null) {
                    navController.navigate(Screens.EditScreen.name)
                } else navController.navigate("${Screens.EditScreen.name}?$TO_EDIT_TODO_ID_KEY=$it")
            },
            updateList = updateList,
        ).Create()
    }

    @Composable
    private fun CreateEditScreen(viewModel: EditViewModel) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val arguments = navBackStackEntry?.arguments
        val editedTodoId = arguments?.getString(TO_EDIT_TODO_ID_KEY, null)

        EditScreen(
            viewModel = viewModel,
            onCancelClick = { navController.popBackStack() },
            toTodosScreen = { navController.navigate("${Screens.TodosScreen.name}?$UPDATE_LIST_KEY=true") },
            editedTodoId = if (editedTodoId.isNullOrBlank()) null else editedTodoId
        ).Create()
    }

    companion object {
        const val UPDATE_LIST_KEY = "UPDATE_LIST_KEY"
        const val TO_EDIT_TODO_ID_KEY = "TO_EDIT_TODO_KEY"
        const val DELETE_KEY = "DELETE_KEY"
    }
}
