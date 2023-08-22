package com.google.credentialmanager.sample.presentation.signup

import android.app.Activity
import android.content.Context
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.credentials.CreatePasswordRequest
import androidx.credentials.CreatePasswordResponse
import androidx.credentials.CreatePublicKeyCredentialRequest
import androidx.credentials.CreatePublicKeyCredentialResponse
import androidx.credentials.CredentialManager
import androidx.credentials.exceptions.CreateCredentialCancellationException
import androidx.credentials.exceptions.CreateCredentialCustomException
import androidx.credentials.exceptions.CreateCredentialException
import androidx.credentials.exceptions.CreateCredentialInterruptedException
import androidx.credentials.exceptions.CreateCredentialProviderConfigurationException
import androidx.credentials.exceptions.CreateCredentialUnknownException
import androidx.credentials.exceptions.publickeycredential.CreatePublicKeyCredentialDomException
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.credentialmanager.sample.data.local.readFromAsset
import com.google.credentialmanager.sample.data.local.showErrorAlert
import com.google.credentialmanager.sample.domain.UIEventUser
import com.google.credentialmanager.sample.domain.User
import com.google.credentialmanager.sample.domain.navigation.IUIActions
import com.google.credentialmanager.sample.domain.navigation.UIActions
import com.google.credentialmanager.sample.presentation.core.navigation.graphs.Graph
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.SecureRandom
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val uiActions: IUIActions
) : ViewModel() {

    private lateinit var credentialManager: CredentialManager

    var userRequest: User by mutableStateOf(User())
        private set

    var userNameError: Boolean by mutableStateOf(false)
        private set
    var passwordError: Boolean by mutableStateOf(false)
        private set

    var showPasswordField: Boolean by mutableStateOf(false)
        private set
    var showProgressIndicator: Boolean by mutableStateOf(false)
        private set


    fun doUIAction(action: UIActions) {
        viewModelScope.launch(Dispatchers.Main) {
            uiActions.doAction(action)
        }
    }

    fun setValues(event: UIEventUser) {
        userRequest = when (event) {
            is UIEventUser.Username -> userRequest.copy(username = event.username)
            is UIEventUser.Password -> userRequest.copy(password = event.password)
            else -> {
                userRequest
            }
        }
    }

    fun signUpWithPassword(context: Context) {
        showPasswordField = true
        userNameError = userRequest.username.isEmpty()
        passwordError = userRequest.password.isEmpty()

        if (!userNameError && !passwordError) {
            showProgressIndicator = true
            viewModelScope.launch(Dispatchers.IO) {
                createPassword(context)
                simulateServerDelayAndLogIn()
            }
        }
    }


    private fun simulateServerDelayAndLogIn() {
        Thread.sleep(2000)
//            DataProvider.setSignedInThroughPasskeys(false)  TODO change for a DB
        doUIAction(
            UIActions.NavigateTo(Graph.HOME)
        )
    }

    fun signUpWithPasskeys(context: Activity) {
        credentialManager = CredentialManager.create(context)
        showPasswordField = false
        passwordError = false
        userNameError = userRequest.username.isEmpty()

        if (!userNameError) {
            viewModelScope.launch(Dispatchers.IO) {
                val data = createPasskey(context)
                data?.let {
                    registerResponse()
//                    DataProvider.setSignedInThroughPasskeys(true) TODO cange for a DB
                    UIActions.NavigateTo(Graph.HOME)
                }
            }
        }
    }


    private fun fetchRegistrationJsonFromServer(context: Context): String {
        var response = context.readFromAsset("RegFromServer")
        response = response.replace("<userId>", getEncodedUserId())
            .replace("<userName>", userRequest.username)
            .replace("<userDisplayName>", userRequest.username)
            .replace("<challenge>", getEncodedChallenge())
        Log.e("archivo", response)
        //Update userId, name and Display name in the mock
        return response
    }

    private fun getEncodedUserId(): String {
        val random = SecureRandom()
        val bytes = ByteArray(64)
        random.nextBytes(bytes)
        return Base64.encodeToString(
            bytes,
            Base64.NO_WRAP or Base64.URL_SAFE or Base64.NO_PADDING
        )
    }


    private fun getEncodedChallenge(): String {
        val random = SecureRandom()
        val bytes = ByteArray(32)
        random.nextBytes(bytes)
        return Base64.encodeToString(
            bytes,
            Base64.NO_WRAP or Base64.URL_SAFE or Base64.NO_PADDING
        )
    }

    private suspend fun createPassword(context: Context) {

        viewModelScope.launch {
            val request = CreatePasswordRequest(
                userRequest.username,
                userRequest.password
            )
            try {
                credentialManager.createCredential(
                    context as FragmentActivity,
                    request
                ) as CreatePasswordResponse
            } catch (e: Exception) {
                Log.e("Auth", "createPassword failed with exception: " + e.message)
            }
        }
    }

    private suspend fun createPasskey(context: Activity): CreatePublicKeyCredentialResponse? {
        val request = CreatePublicKeyCredentialRequest(
            fetchRegistrationJsonFromServer(context),
            preferImmediatelyAvailableCredentials = false
        )
        var response: CreatePublicKeyCredentialResponse? = null
        try {
            response = credentialManager.createCredential(
                context,
                request
            ) as CreatePublicKeyCredentialResponse
            Log.e("SUCCESS", "SUCCESS")
        } catch (e: CreateCredentialException) {
            Log.e("CreateCredentialEx: ", e.stackTrace.toString())
            handlePasskeyFailure(context, e)
        }

        return response
    }


    private fun handlePasskeyFailure(context: Context, e: CreateCredentialException) {
        val msg = when (e) {
            is CreatePublicKeyCredentialDomException -> {
                "An error occurred while creating a passkey, please check logs for additional details."
            }

            is CreateCredentialCancellationException -> {
                "The user intentionally canceled the operation and chose not to register the credential. Check logs for additional details."
            }

            is CreateCredentialInterruptedException -> {
                "The operation was interrupted, please retry the call. Check logs for additional details."
            }

            is CreateCredentialProviderConfigurationException -> {
                "Your app is missing the provider configuration dependency. Check logs for additional details."
            }

            is CreateCredentialUnknownException -> {
                "An unknown error occurred while creating passkey. Check logs for additional details."
            }

            is CreateCredentialCustomException -> {
                "An unknown error occurred from a 3rd party SDK. Check logs for additional details."
            }

            else -> {
                Log.w("Auth", "Unexpected exception type ${e::class.java.name}")
                "An unknown error occurred."
            }
        }
        Log.e("Auth", "createPasskey failed with exception: " + e.stackTraceToString())
        context.showErrorAlert(msg)
    }

    private fun registerResponse(): Boolean {
        return true
    }

}