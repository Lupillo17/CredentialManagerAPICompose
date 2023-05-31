package com.example.credentialmanagerapicompose.presentation.core.navigation

import androidx.navigation.NavHostController
import com.example.credentialmanagerapicompose.domain.navigation.INavigationProvider
import com.example.credentialmanagerapicompose.domain.navigation.INavigationService

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