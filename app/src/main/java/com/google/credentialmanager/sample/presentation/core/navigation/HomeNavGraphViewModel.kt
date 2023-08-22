package com.google.credentialmanager.sample.presentation.core.navigation

import androidx.lifecycle.ViewModel
import com.google.credentialmanager.sample.domain.navigation.IUIActions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeNavGraphViewModel @Inject constructor(
    val uiActions: IUIActions
) : ViewModel()
