package com.test.dvt.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.test.dvt.presentation.navigation.BottomNavRoutes

@Composable
fun BottomAppBarComponent(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val screens = listOf(
        BottomNavRoutes.Weather,
        BottomNavRoutes.Forecast,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Row(
        modifier = modifier
            .height(60.dp)
            .fillMaxSize()
            .clip(RectangleShape)
            .padding(start = 5.dp, end = 5.dp)
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
            ),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            SingleBottomAppItem(
                bottomNavRoutes = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.SingleBottomAppItem(
    bottomNavRoutes: BottomNavRoutes,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == bottomNavRoutes.route } == true
    val contentColor =
        if (selected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground

    Box(
        modifier = Modifier
            .clickable(
                onClick = {
                    navController.navigate(bottomNavRoutes.route) {
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
                text = bottomNavRoutes.title,
                style = TextStyle(
                    color = contentColor,
                    fontWeight = FontWeight.Bold
                )
            )

        }
    }
}
