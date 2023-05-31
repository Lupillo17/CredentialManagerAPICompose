package com.example.credentialmanagerapicompose.domain

import androidx.annotation.StringRes

interface StringProvider {
    fun getString(@StringRes id: Int): String
}