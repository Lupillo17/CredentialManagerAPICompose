package com.google.credentialmanager.sample.presentation.core.actions

import com.google.credentialmanager.sample.domain.navigation.IUIActions
import com.google.credentialmanager.sample.domain.navigation.UIActions
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class UIActionImpl: IUIActions {

    private val actionsFlow = MutableSharedFlow<UIActions>()

    override fun onAction(): SharedFlow<UIActions> = actionsFlow

    override suspend fun doAction(action: UIActions) {
        actionsFlow.emit(action)
    }
}