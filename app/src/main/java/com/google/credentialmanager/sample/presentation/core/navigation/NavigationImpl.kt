package com.google.credentialmanager.sample.presentation.core.navigation

import androidx.navigation.NavHostController
import com.google.credentialmanager.sample.domain.navigation.INavigationProvider
import com.google.credentialmanager.sample.domain.navigation.INavigationService

class NavigationImpl : INavigationService, INavigationProvider {

    private lateinit var navController: NavHostController

    override fun getNavController(): NavHostController = navController

    override fun setNavController(navController: NavHostController) {
        this.navController = navController
    }

    override fun navigateTo(route: String) {
        navController.navigate(route)
    }

    override fun goBack() {
        navController.popBackStack()
    }
}