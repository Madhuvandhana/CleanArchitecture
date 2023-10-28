package com.example.cleanarchitecture.presentation.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.data.util.Resource
import com.example.cleanarchitecture.domain.repository.MainRepository
import com.example.cleanarchitecture.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.take
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
) : BaseViewModel<ViewIntent, MCUViewState, SingleEvent>(), DefaultLifecycleObserver {
    override val viewState: StateFlow<MCUViewState>

    init {
        val initialVS = MCUViewState.initial()
        viewState = merge(
            intentSharedFlow.filterIsInstance<ViewIntent.Initial>().take(1),
            intentSharedFlow.filterNot { it is ViewIntent.Initial },
        )
            .shareWhileSubscribed()
            .toPartialStateChangeFlow()
            .sendSingleEvent()
            .scan(initialVS) { vs, change -> change.reduce(vs) }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                initialVS,
            )
    }

    private fun Flow<ViewIntent.Initial>.toGetUsersChangeFlow(): Flow<PartialStateChange.GetUser> = flow {
        repository.getUsers().stateIn(viewModelScope).collect { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let { users ->
                        emit(PartialStateChange.GetUser.GetUserSuccess(users))
                    }
                }

                is Resource.Error -> {
                    result.message?.let {
                        emit(
                            PartialStateChange.GetUser.GetUserFailure(
                                emptyList(),
                                it,
                            ),
                        )
                    }
                }

                is Resource.Loading -> {
                    emit(PartialStateChange.GetUser.Loading)
                }
            }
        }
    }

    private fun Flow<PartialStateChange>.sendSingleEvent(): Flow<PartialStateChange> = emptyFlow()

    private fun SharedFlow<ViewIntent>.toPartialStateChangeFlow(): Flow<PartialStateChange> {
        return merge(
            filterIsInstance<ViewIntent.Initial>()
                .toGetUsersChangeFlow(),
        )
    }
}
