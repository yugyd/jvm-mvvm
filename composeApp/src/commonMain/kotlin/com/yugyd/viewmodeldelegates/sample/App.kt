package com.yugyd.viewmodeldelegates.sample

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yugyd.viewmodeldelegates.sample.home.di.buildHomeBinder
import com.yugyd.viewmodeldelegates.sample.home.ui.HomeScreen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource
import viewmodeldelegates.composeapp.generated.resources.Res
import viewmodeldelegates.composeapp.generated.resources.ic_account_circle

@Composable
fun App() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            imageVector = vectorResource(it.icon),
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            when (currentDestination) {
                AppDestinations.HOME -> {
                    HomeScreen(
                        binder = viewModel(
                            initializer = {
                                buildHomeBinder()
                            },
                        ),
                        modifier = Modifier.padding(innerPadding),
                        onNavigateToFavourites = {
                            currentDestination = AppDestinations.FAVORITES
                        },
                    )
                }

                AppDestinations.FAVORITES -> {
                    Greeting(
                        name = "Favorites",
                        modifier = Modifier.padding(innerPadding)
                    )
                }

                AppDestinations.PROFILE -> {
                    Greeting(
                        name = "Profile",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: DrawableResource,
) {
    HOME("Home", Res.drawable.ic_account_circle),
    FAVORITES("Favorites", Res.drawable.ic_account_circle),
    PROFILE("Profile", Res.drawable.ic_account_circle),
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
