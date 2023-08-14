package com.test.dvt.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.test.dvt.presentation.navigation.MainNavRoutes

@Composable
fun SingleBottomAppItem(
    mainNavRoutes: MainNavRoutes,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == mainNavRoutes.route } == true
    val contentColor =
        if (selected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground

    Box(
        modifier = Modifier
            .clickable(
                onClick = {
                    navController.navigate(mainNavRoutes.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
            )
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = mainNavRoutes.title,
                style = TextStyle(
                    color = contentColor,
                    fontWeight = FontWeight.Bold
                )
            )

        }
    }
}
