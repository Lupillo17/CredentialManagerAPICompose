package com.google.credentialmanager.sample.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.google.credentialmanager.sample.domain.navigation.INavigationProvider
import com.google.credentialmanager.sample.presentation.core.navigation.HomeNavGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigationProvider: INavigationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            navigationProvider.setNavController(navController)
            HomeNavGraph(navigationProvider)
        }
    }
}


