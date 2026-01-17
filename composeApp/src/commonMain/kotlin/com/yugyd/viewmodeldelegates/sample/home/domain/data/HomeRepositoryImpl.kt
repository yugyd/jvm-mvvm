package com.yugyd.viewmodeldelegates.sample.home.domain.data

import com.yugyd.viewmodeldelegates.sample.home.domain.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomeRepositoryImpl(
    private val ioCoroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : HomeRepository {

    override fun getData(userName: String) = flow {
        delay(5000L) // Simulate network delay
        emit("Sample Data from Repository for user: $userName")
    }
        .flowOn(ioCoroutineDispatcher)
}
