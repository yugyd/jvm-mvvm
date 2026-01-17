package com.yugyd.viewmodeldelegates.sample.home.ui

import com.yugyd.viewmodeldelegates.sample.home.domain.HomeViewModel
import com.yugyd.viewmodeldelegates.sample.home.domain.HomeViewModel.Event
import com.yugyd.viewmodeldelegates.sample.home.domain.HomeViewModel.State
import com.yugyd.viewmodeldelegates.ui.StateViewModelBinder

class SimpleHomeBinder(
    private val viewModel: HomeViewModel,
    dispatchersProvider: DispatchersProvider,
) : StateViewModelBinder<Event, State>(
    viewModel = viewModel,
    mainImmediateCoroutineDispatcher = dispatchersProvider.main,
) {
    fun onEvent(event: Event) = viewModel.accept(event)
}
