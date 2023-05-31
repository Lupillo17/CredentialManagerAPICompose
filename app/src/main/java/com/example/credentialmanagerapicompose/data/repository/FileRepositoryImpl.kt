package com.example.credentialmanagerapicompose.data.repository

import android.content.Context
import com.example.credentialmanagerapicompose.domain.repository.FileRepository
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val context: Context
) : FileRepository {
    override fun readFileFromAssets(fileName: String): String {
        val fileContent = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
        return fileContent
    }
}