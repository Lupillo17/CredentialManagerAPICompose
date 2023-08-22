package com.google.credentialmanager.sample.domain.navigation

import androidx.navigation.NavHostController

interface INavigationProvider {
    fun getNavController(): NavHostController
    fun setNavController(navController: NavHostController)
}