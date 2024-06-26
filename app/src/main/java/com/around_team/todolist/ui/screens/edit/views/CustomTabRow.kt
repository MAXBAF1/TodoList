package com.around_team.todolist.ui.screens.edit.views

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.around_team.todolist.R
import com.around_team.todolist.ui.common.enums.TodoPriority
import com.around_team.todolist.ui.common.enums.getIconColor
import com.around_team.todolist.ui.theme.JetTodoListTheme

class CustomTabRow(
    private val selectedTab: Int,
    private val tabList: Array<TodoPriority>,
    private val onTabChanged: (Int) -> Unit,
    private val modifier: Modifier = Modifier,
) {

    @Composable
    fun Create() {
        val indicator = @Composable { tabPositions: List<TabPosition> ->
            CustomIndicator(tabPositions, selectedTab)
        }
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(9.dp))
                .background(JetTodoListTheme.colors.support.overlay)
        ) {
            TabRow(
                modifier = Modifier.padding(2.dp),
                containerColor = Color.Transparent,
                selectedTabIndex = selectedTab,
                indicator = indicator,
                divider = {},
            ) {
                tabList.forEachIndexed { index, themeTab ->
                    CustomTab(
                        tab = themeTab,
                        onClick = {
                            onTabChanged(index)

                        },
                    )
                }
            }
        }
    }

    @Composable
    private fun CustomIndicator(tabPositions: List<TabPosition>, selectedTab: Int) {
        val transition = updateTransition(selectedTab, label = "")
        val indicatorStart by transition.animateDp(
            transitionSpec = {
                if (initialState < targetState) {
                    spring(dampingRatio = 1f, stiffness = 250f)
                } else {
                    spring(dampingRatio = 1f, stiffness = 1000f)
                }
            }, label = ""
        ) { tabPositions[it].left }

        val indicatorEnd by transition.animateDp(
            transitionSpec = {
                if (initialState < targetState) {
                    spring(dampingRatio = 1f, stiffness = 1000f)
                } else {
                    spring(dampingRatio = 1f, stiffness = 250f)
                }
            }, label = ""
        ) { tabPositions[it].right }

        Box(
            Modifier
                .offset(x = indicatorStart)
                .wrapContentSize(align = Alignment.BottomStart)
                .width(indicatorEnd - indicatorStart)
                .fillMaxSize()
                .background(color = JetTodoListTheme.colors.back.elevated, RoundedCornerShape(7.dp))

        )
    }

    @Composable
    private fun CustomTab(tab: TodoPriority, onClick: () -> Unit) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(7.dp))
                .size(48.dp, 32.dp)
                .zIndex(1f)
                .clickable(
                    onClick = onClick,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ),
            contentAlignment = Alignment.Center,
        ) {
            if (tab.iconId != null) {
                Icon(
                    painter = painterResource(id = tab.iconId),
                    contentDescription = stringResource(id = R.string.priority_icon),
                    tint = tab.getIconColor()
                )
            } else if (tab.text != null) {
                Text(
                    text = stringResource(id = tab.text),
                    style = JetTodoListTheme.typography.body,
                    color = JetTodoListTheme.colors.label.primary
                )
            }
        }
    }
}