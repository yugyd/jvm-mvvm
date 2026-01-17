package com.yugyd.viewmodeldelegates.sample.home.domain

import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getData(userName: String): Flow<String>
}
