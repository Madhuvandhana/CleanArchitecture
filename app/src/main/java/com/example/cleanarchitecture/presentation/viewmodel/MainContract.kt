package com.example.cleanarchitecture.presentation.viewmodel

import androidx.compose.runtime.Immutable
import com.example.cleanarchitecture.domain.model.User
import com.example.cleanarchitecture.presentation.common.MviIntent
import com.example.cleanarchitecture.presentation.common.MviSingleEvent
import com.example.cleanarchitecture.presentation.common.MviViewState

@Immutable
data class MCUViewState(
    val users: List<User>,
    val isLoading: Boolean,
    val error: String,
) : MviViewState {
    companion object {
        fun initial() = MCUViewState(
            users = emptyList(),
            isLoading = false,
            error = "",
        )
    }
}

@Immutable
sealed interface ViewIntent : MviIntent {
    object Initial : ViewIntent
}

sealed interface SingleEvent : MviSingleEvent

internal sealed interface PartialStateChange {
    fun reduce(viewState: MCUViewState): MCUViewState

    sealed interface GetUser : PartialStateChange {
        object Loading : GetUser

        data class GetUserSuccess(
            val users: List<User>,
        ) : GetUser

        data class GetUserFailure(
            val users: List<User>,
            val error: String,
        ) : GetUser

        override fun reduce(viewState: MCUViewState): MCUViewState =
            when (this) {
                Loading -> viewState.copy(isLoading = true)
                is GetUserSuccess -> viewState.copy(isLoading = false, users = users)
                is GetUserFailure -> viewState.copy(isLoading = false, users = users)
            }
    }
}
