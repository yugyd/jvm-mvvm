package com.yugyd.viewmodeldelegates.sample.home.ui

import com.yugyd.viewmodeldelegates.sample.home.domain.HomeViewModel.State
import com.yugyd.viewmodeldelegates.sample.home.domain.HomeViewModel.State.NavigationState
import com.yugyd.viewmodeldelegates.sample.home.ui.HomeBinder.Model
import com.yugyd.viewmodeldelegates.sample.home.ui.HomeBinder.Model.NavigationUiState
import com.yugyd.viewmodeldelegates.ui.StateToModelMapper

class HomeMapper : StateToModelMapper<State, Model> {

    override fun map(state: State): Model {
        return Model(
            isLoading = state.isLoading,
            isWarning = state.isWarning,
            message = state.message,
            showErrorMessage = state.showErrorMessage,
            navigationState = mapNavigationState(state.navigationState),
        )
    }

    private fun mapNavigationState(navigationState: NavigationState?): NavigationUiState? {
        return when (navigationState) {
            is NavigationState.NavigateToFavourites -> {
                NavigationUiState.NavigateToFavourites
            }

            null -> null
        }
    }
}
