package com.yugyd.viewmodeldelegates.sample.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yugyd.viewmodeldelegates.sample.home.ui.HomeBinder.Model.NavigationUiState

@Composable
fun HomeScreen(
    binder: HomeBinder,
    modifier: Modifier = Modifier,
    onNavigateToFavourites: () -> Unit = {},
) {
    val state by binder.model.collectAsStateWithLifecycle()
    val snackbarHostState = SnackbarHostState()

    HomeContent(
        message = state.message,
        isLoading = state.isLoading,
        isWarning = state.isWarning,
        onActionClicked = binder::onActionClicked,
        modifier = modifier,
    )

    ErrorMessageEffect(
        showErrorMessage = state.showErrorMessage,
        snackbarHostState = snackbarHostState,
        onErrorDismissState = binder::onSnackbarDismissed,
    )

    NavigationHandlerEffect(
        navigationState = state.navigationState,
        onNavigateToFavourites = onNavigateToFavourites,
        onNavigationHandled = binder::onNavigationHandled,
    )
}

@Composable
fun HomeContent(
    message: String,
    isLoading: Boolean,
    isWarning: Boolean,
    onActionClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when {
        isLoading -> {
            Text(
                text = "Loading...",
                modifier = modifier
            )
        }

        isWarning -> {
            Text(
                text = "Warning!",
                modifier = modifier
            )
        }

        else -> {
            Column(modifier = modifier) {
                Text(
                    text = "Hello $message!",
                    modifier = modifier
                )

                Button(
                    onClick = onActionClicked,
                ) {
                    Text(text = "Go to Favourites")
                }
            }
        }
    }
}

@Composable
fun ErrorMessageEffect(
    showErrorMessage: Boolean,
    snackbarHostState: SnackbarHostState,
    onErrorDismissState: () -> Unit,
) {
    LaunchedEffect(key1 = showErrorMessage) {
        if (showErrorMessage) {
            snackbarHostState.showSnackbar(message = "An error occurred")

            onErrorDismissState()
        }
    }
}

@Composable
internal fun NavigationHandlerEffect(
    navigationState: NavigationUiState?,
    onNavigateToFavourites: () -> Unit,
    onNavigationHandled: () -> Unit,
) {
    LaunchedEffect(key1 = navigationState) {
        when (navigationState) {
            is NavigationUiState.NavigateToFavourites -> onNavigateToFavourites()
            null -> Unit
        }

        navigationState?.let { onNavigationHandled() }
    }
}
