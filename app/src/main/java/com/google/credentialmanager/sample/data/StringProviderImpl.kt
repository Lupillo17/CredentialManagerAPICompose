package com.google.credentialmanager.sample.data

import android.content.Context
import com.google.credentialmanager.sample.domain.StringProvider
import javax.inject.Inject

class StringProviderImpl @Inject constructor(
    private val context: Context
) : StringProvider {
    override fun getString(id: Int): String {
        return context.getString(id)
    }
}