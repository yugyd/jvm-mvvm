package com.yugyd.viewmodeldelegates.sample.home.domain

import com.yugyd.viewmodeldelegates.ViewModelDelegates
import com.yugyd.viewmodeldelegates.sample.home.domain.HomeViewModel.Event
import com.yugyd.viewmodeldelegates.sample.home.domain.HomeViewModel.State
import com.yugyd.viewmodeldelegates.sample.home.domain.HomeViewModel.State.NavigationState
import kotlinx.coroutines.CoroutineScope

class OnActionClickedViewModelDelegate : HomeViewModelDelegate {

    override fun accept(
        event: Event,
        viewModel: ViewModelDelegates<Event, State>,
        scope: CoroutineScope,
        getState: () -> State
    ): Boolean {
        if (event != Event.OnActionClicked) return false

        viewModel.updateState {
            it.copy(navigationState = NavigationState.NavigateToFavourites)
        }

        return true
    }
}
