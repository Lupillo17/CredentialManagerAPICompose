package com.example.credentialmanagerapicompose.data

import android.content.Context
import com.example.credentialmanagerapicompose.domain.StringProvider
import javax.inject.Inject

class StringProviderImpl @Inject constructor(
    private val context: Context
) : StringProvider {
    override fun getString(id: Int): String {
        return context.getString(id)
    }
}