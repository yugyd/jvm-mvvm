package com.yugyd.viewmodeldelegates.sample.home.domain

import com.yugyd.viewmodeldelegates.ViewModelDelegates
import com.yugyd.viewmodeldelegates.sample.home.domain.HomeViewModel.Event
import com.yugyd.viewmodeldelegates.sample.home.domain.HomeViewModel.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LoadDataViewModelDelegate(
    private val repository: HomeRepository,
) : HomeViewModelDelegate {

    override fun accept(
        event: Event,
        viewModel: ViewModelDelegates<Event, State>,
        scope: CoroutineScope,
        getState: () -> State
    ): Boolean {
        if (event != Event.LoadData) return false

        viewModel.updateState {
            it.copy(
                isLoading = true,
                isWarning = false,
                message = "",
            )
        }

        scope.launch {
            val currentState = getState()
            val userName = currentState.arguments.userName

            repository
                .getData(userName = userName)
                .catch { error ->
                    error.printStackTrace()

                    viewModel.updateState {
                        it.copy(
                            isLoading = false,
                            isWarning = true,
                            message = "",
                        )
                    }
                }
                .collect { newData ->
                    viewModel.updateState {
                        it.copy(
                            isLoading = false,
                            isWarning = false,
                            message = newData,
                        )
                    }
                }
        }

        return true
    }
}
