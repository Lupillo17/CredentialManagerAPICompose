package com.example.credentialmanagerapicompose.domain.navigation

import kotlinx.coroutines.flow.SharedFlow

interface IUIActions {
    // Escucha las acciones
    fun onAction(): SharedFlow<UIActions>

    // El que va a proveer las acciones
    suspend fun doAction(action: UIActions)
}

