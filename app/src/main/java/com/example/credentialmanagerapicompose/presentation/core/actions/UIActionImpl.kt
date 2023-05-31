package com.example.credentialmanagerapicompose.presentation.core.actions

import com.example.credentialmanagerapicompose.domain.navigation.IUIActions
import com.example.credentialmanagerapicompose.domain.navigation.UIActions
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class UIActionImpl: IUIActions {

    private val actionsFlow = MutableSharedFlow<UIActions>()

    override fun onAction(): SharedFlow<UIActions> = actionsFlow

    override suspend fun doAction(action: UIActions) {
        actionsFlow.emit(action)
    }
}