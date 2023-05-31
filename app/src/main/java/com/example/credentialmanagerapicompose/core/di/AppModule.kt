package com.example.credentialmanagerapicompose.core.di

import android.content.Context
import com.example.credentialmanagerapicompose.domain.navigation.INavigationProvider
import com.example.credentialmanagerapicompose.domain.navigation.INavigationService
import com.example.credentialmanagerapicompose.domain.navigation.IUIActions
import com.example.credentialmanagerapicompose.presentation.core.navigation.NavigationImpl
import com.example.credentialmanagerapicompose.presentation.core.actions.UIActionImpl
import com.example.credentialmanagerapicompose.data.StringProviderImpl
import com.example.credentialmanagerapicompose.data.repository.FileRepositoryImpl
import com.example.credentialmanagerapicompose.domain.StringProvider
import com.example.credentialmanagerapicompose.domain.repository.FileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFileRepository(@ApplicationContext context: Context): FileRepository {
        return FileRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideStringResource(@ApplicationContext context: Context): StringProvider {
        return StringProviderImpl(context)
    }

    @Provides
    @Singleton
    fun provideNavigationImplementation(): NavigationImpl {
        return NavigationImpl()
    }

    @Provides
    @Singleton
    fun provideNavigationProvider(navigationImpl: NavigationImpl): INavigationProvider {
        return navigationImpl
    }

    @Provides
    @Singleton
    fun provideNavigationService(navigationImpl: NavigationImpl): INavigationService {
        return navigationImpl
    }

    @Provides
    @Singleton
    fun provideUIAction(): IUIActions {
        return UIActionImpl()
    }


}