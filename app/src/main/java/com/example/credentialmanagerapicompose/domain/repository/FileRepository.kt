package com.example.credentialmanagerapicompose.domain.repository

interface FileRepository {
    fun readFileFromAssets(fileName: String): String
}