package com.google.credentialmanager.sample.domain

import androidx.annotation.StringRes

interface StringProvider {
    fun getString(@StringRes id: Int): String
}