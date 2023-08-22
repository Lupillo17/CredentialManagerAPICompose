package com.google.credentialmanager.sample.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.credentialmanager.sample.domain.navigation.IUIActions
import com.google.credentialmanager.sample.domain.navigation.UIActions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val uiActions: IUIActions
) : ViewModel() {

    fun doUIAction(action: UIActions) {
        viewModelScope.launch(Dispatchers.Main) {
            uiActions.doAction(action)
        }
    }

}