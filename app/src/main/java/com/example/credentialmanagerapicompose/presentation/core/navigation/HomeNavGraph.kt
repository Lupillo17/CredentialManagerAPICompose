package com.example.credentialmanagerapicompose.presentation.core.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.credentialmanagerapicompose.presentation.core.navigation.graphs.Graph
import com.example.credentialmanagerapicompose.domain.navigation.INavigationProvider
import com.example.credentialmanagerapicompose.domain.navigation.UIActions
import com.example.credentialmanagerapicompose.presentation.home.HomeView
import com.example.credentialmanagerapicompose.presentation.signin.SigninView

@Composable
fun HomeNavGraph(
    navigationProvider: INavigationProvider,
    viewModel: HomeNavGraphViewModel = hiltViewModel()
) {

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
        startDestination = Graph.ROOT
    ) {
        composable(route = Graph.ROOT) {
            HomeView()
        }
        composable(route = Graph.SIGN_IN) {
            SigninView()
        }
        composable(route = Graph.SIGN_UP) {
            SigninView() // TODO: sign up view
        }
    }
}