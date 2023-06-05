package com.example.credentialmanagerapicompose.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.credentialmanagerapicompose.R
import com.example.credentialmanagerapicompose.domain.navigation.IUIActions
import com.example.credentialmanagerapicompose.domain.navigation.UIActions
import com.example.credentialmanagerapicompose.data.local.DataProvider
import com.example.credentialmanagerapicompose.domain.StringProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
//    private val navigationService: INavigationService,
    private val stringProvider: StringProvider,
    private val uiAction: IUIActions,
) : ViewModel() {

    private val _signedInText = mutableStateOf("")
    val signedInText get() = _signedInText

    init {
        configureSignedInText()
    }

    private fun configureSignedInText() {
        if (DataProvider.isSignedInThroughPasskeys()) {
            _signedInText.value = stringProvider.getString(R.string.logged_in_through_passkeys)
        } else {
            _signedInText.value = stringProvider.getString(R.string.logged_in_through_password)
        }
    }

    fun doUIAction(action: UIActions) {
//        navigationService.navigateTo(Graph.SIGN_IN)
        viewModelScope.launch(Dispatchers.Main) {
            uiAction.doAction(action)
        }
    }
}