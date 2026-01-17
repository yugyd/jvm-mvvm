package com.yugyd.viewmodeldelegates.sample.home.ui

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class DispatchersProviderImpl(
    override val io: CoroutineDispatcher = Dispatchers.IO,
    override val default: CoroutineDispatcher = Dispatchers.Default,
    override val main: CoroutineDispatcher = Dispatchers.Main.immediate,
) : DispatchersProvider
