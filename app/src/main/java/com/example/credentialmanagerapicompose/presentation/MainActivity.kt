package com.example.credentialmanagerapicompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.credentialmanagerapicompose.presentation.core.navigation.HomeNavGraph
import com.example.credentialmanagerapicompose.domain.navigation.INavigationProvider
import com.example.credentialmanagerapicompose.presentation.core.designSystem.ui.theme.CredentialManagerAPIComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationProvider: INavigationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CredentialManagerAPIComposeTheme {
                val navController = rememberNavController()
                navigationProvider.setNavController(navController)
                HomeNavGraph(navigationProvider)
            }
        }
    }
}


