package com.example.credentialmanagerapicompose.presentation.core.navigation

import androidx.lifecycle.ViewModel
import com.example.credentialmanagerapicompose.domain.navigation.IUIActions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeNavGraphViewModel @Inject constructor(
    val uiActions: IUIActions
) : ViewModel()
