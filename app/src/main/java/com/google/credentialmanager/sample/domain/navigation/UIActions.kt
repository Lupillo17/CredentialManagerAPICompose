package com.google.credentialmanager.sample.domain.navigation

sealed class UIActions {
    data class NavigateTo(val route: String) : UIActions()
    data class ShowToast(val message: String) : UIActions()
}