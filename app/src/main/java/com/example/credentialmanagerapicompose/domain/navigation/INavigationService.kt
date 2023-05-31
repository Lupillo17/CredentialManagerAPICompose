package com.example.credentialmanagerapicompose.domain.navigation

interface INavigationService {
    fun navigateTo(route: String)
    fun goBack()
}