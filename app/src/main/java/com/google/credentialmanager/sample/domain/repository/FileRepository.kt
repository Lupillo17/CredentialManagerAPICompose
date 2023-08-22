package com.google.credentialmanager.sample.domain.repository

interface FileRepository {
    fun readFileFromAssets(fileName: String): String
}