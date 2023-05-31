package com.example.credentialmanagerapicompose.domain.navigation

sealed class UIActions {
    data class NavigateTo(val route: String) : UIActions()
    data class ShowToast(val message: String) : UIActions()
}