package com.google.credentialmanager.sample.presentation.core.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.credentialmanager.sample.presentation.core.navigation.graphs.Graph
import com.google.credentialmanager.sample.domain.navigation.INavigationProvider
import com.google.credentialmanager.sample.domain.navigation.UIActions
import com.google.credentialmanager.sample.presentation.home.HomeView
import com.google.credentialmanager.sample.presentation.main.MainView
import com.google.credentialmanager.sample.presentation.signin.SigninView
import com.google.credentialmanager.sample.presentation.signup.SignupView

@Composable
fun HomeNavGraph(
    navigationProvider: INavigationProvider,
    viewModel: HomeNavGraphViewModel = hiltViewModel()
) {

    // is used to show a toast cmtjg with UiAction
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.uiActions.onAction().collect() {
            when (it) {
                is UIActions.NavigateTo -> {
                    navigationProvider.getNavController().navigate(it.route)
                }

                is UIActions.ShowToast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    NavHost(
        navController = navigationProvider.getNavController(),
        startDestination = Graph.MAIN
    ) {
        composable(route = Graph.MAIN) {
            MainView()
        }
        composable(route = Graph.HOME) {
            HomeView()
        }
        composable(route = Graph.SIGN_IN) {
            SigninView()
        }
        composable(route = Graph.SIGN_UP) {
            SignupView() // TODO: sign up view
        }
    }
}