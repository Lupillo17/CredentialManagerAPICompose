package com.example.credentialmanagerapicompose.presentation.signup

import android.os.Handler
import android.os.Looper
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.credentialmanagerapicompose.domain.navigation.IUIActions
import com.example.credentialmanagerapicompose.domain.navigation.UIActions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.SecureRandom
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val uiActions: IUIActions
) : ViewModel() {

    private val _showProgressIndicator = mutableStateOf(false)
    val showProgressIndicator get() = _showProgressIndicator

    var _showProgressIndicator2: Boolean by mutableStateOf(false)
        private set


    fun doUIAction(action: UIActions) {
        viewModelScope.launch(Dispatchers.Main) {
            uiActions.doAction(action)
        }
    }

//    private fun signUpWithPassword(): View.OnClickListener {
//        return View.OnClickListener {
//            binding.password.visibility = View.VISIBLE
//
//            if (binding.username.text.isNullOrEmpty()) {
//                binding.username.error = "User name required"
//                binding.username.requestFocus()
//            } else if (binding.password.text.isNullOrEmpty()) {
//                binding.password.error = "Password required"
//                binding.password.requestFocus()
//            } else {
//                lifecycleScope.launch {
//
//                    configureViews(View.VISIBLE, false)
//
//                    createPassword()
//
//                    simulateServerDelayAndLogIn()
//
//                }
//            }
//        }
//    }
//
//    private fun simulateServerDelayAndLogIn() {
//        Handler(Looper.getMainLooper()).postDelayed({
//
//            DataProvider.setSignedInThroughPasskeys(false)
//
//            configureViews(View.INVISIBLE, true)
//
//            listener.showHome()
//        }, 2000)
//    }
//
//    private fun signUpWithPasskeys(): View.OnClickListener {
//        return View.OnClickListener {
//
//            binding.password.visibility = View.GONE
//
//            if (binding.username.text.isNullOrEmpty()) {
//                binding.username.error = "User name required"
//                binding.username.requestFocus()
//            } else {
//                lifecycleScope.launch {
//                    configureViews(View.VISIBLE, false)
//
//                    val data = createPasskey()
//
//                    configureViews(View.INVISIBLE, true)
//
//                    data?.let {
//                        registerResponse()
//                        DataProvider.setSignedInThroughPasskeys(true)
//                        listener.showHome()
//                    }
//                }
//            }
//        }
//    }
//
//    private fun fetchRegistrationJsonFromServer(): String {
//
//        val response = requireContext().readFromAsset("RegFromServer")
//
//        //Update userId, name and Display name in the mock
//        return response.replace("<userId>", getEncodedUserId())
//            .replace("<userName>", binding.username.text.toString())
//            .replace("<userDisplayName>", binding.username.text.toString())
//            .replace("<challenge>", getEncodedChallenge())
//    }
//
//    private fun getEncodedUserId(): String {
//        val random = SecureRandom()
//        val bytes = ByteArray(64)
//        random.nextBytes(bytes)
//        return Base64.encodeToString(
//            bytes,
//            Base64.NO_WRAP or Base64.URL_SAFE or Base64.NO_PADDING
//        )
//    }
//
//    private fun getEncodedChallenge(): String {
//        val random = SecureRandom()
//        val bytes = ByteArray(32)
//        random.nextBytes(bytes)
//        return Base64.encodeToString(
//            bytes,
//            Base64.NO_WRAP or Base64.URL_SAFE or Base64.NO_PADDING
//        )
//    }
//
//    private suspend fun createPassword() {
//        val request = CreatePasswordRequest(
//            binding.username.text.toString(),
//            binding.password.text.toString()
//        )
//        try {
//            credentialManager.createCredential(request, requireActivity()) as CreatePasswordResponse
//        } catch (e: Exception) {
//            Log.e("Auth", "createPassword failed with exception: " + e.message)
//        }
//    }
//
//    private suspend fun createPasskey(): CreatePublicKeyCredentialResponse? {
//        val request = CreatePublicKeyCredentialRequest(fetchRegistrationJsonFromServer())
//        var response: CreatePublicKeyCredentialResponse? = null
//        try {
//            response = credentialManager.createCredential(
//                request,
//                requireActivity()
//            ) as CreatePublicKeyCredentialResponse
//        } catch (e: CreateCredentialException) {
//            configureProgress(View.INVISIBLE)
//            handlePasskeyFailure(e)
//        }
//        return response
//    }
//
//    private fun configureViews(visibility: Int, flag: Boolean) {
//        configureProgress(visibility)
//        binding.signUp.isEnabled = flag
//        binding.signUpWithPassword.isEnabled = flag
//    }
//
//    private fun configureProgress(visibility: Int) {
//        binding.textProgress.visibility = visibility
//        binding.circularProgressIndicator.visibility = visibility
//    }
//
//    private fun handlePasskeyFailure(e: CreateCredentialException) {
//        val msg = when (e) {
//            is CreatePublicKeyCredentialDomException -> {
//                "An error occurred while creating a passkey, please check logs for additional details."
//            }
//            is CreateCredentialCancellationException -> {
//                "The user intentionally canceled the operation and chose not to register the credential. Check logs for additional details."
//            }
//            is CreateCredentialInterruptedException -> {
//                "The operation was interrupted, please retry the call. Check logs for additional details."
//            }
//            is CreateCredentialProviderConfigurationException -> {
//                "Your app is missing the provider configuration dependency. Check logs for additional details."
//            }
//            is CreateCredentialUnknownException -> {
//                "An unknown error occurred while creating passkey. Check logs for additional details."
//            }
//            is CreateCustomCredentialException -> {
//                "An unknown error occurred from a 3rd party SDK. Check logs for additional details."
//            }
//            else -> {
//                Log.w("Auth", "Unexpected exception type ${e::class.java.name}")
//                "An unknown error occurred."
//            }
//        }
//        Log.e("Auth", "createPasskey failed with exception: " + e.message.toString())
//        activity?.showErrorAlert(msg)
//    }
//
//    private fun registerResponse(): Boolean {
//        return true
//    }

}