package com.google.credentialmanager.sample.domain

sealed class UIEventUser {
    data class Username(val username: String) : UIEventUser()
    data class Password(val password: String) : UIEventUser()
}