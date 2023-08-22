package com.google.credentialmanager.sample.domain

data class User(
    val username: String = "",
    val usernameError: Boolean = false,
    val password: String = "",
    val passwordError: Boolean = false,
    val showPasswordField: Boolean = false
)
