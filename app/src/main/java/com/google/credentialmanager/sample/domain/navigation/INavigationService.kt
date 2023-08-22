package com.google.credentialmanager.sample.domain.navigation

interface INavigationService {
    fun navigateTo(route: String)
    fun goBack()
}