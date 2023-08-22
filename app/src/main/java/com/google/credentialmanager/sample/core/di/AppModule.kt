package com.google.credentialmanager.sample.core.di

import android.content.Context
import com.google.credentialmanager.sample.domain.navigation.INavigationProvider
import com.google.credentialmanager.sample.domain.navigation.INavigationService
import com.google.credentialmanager.sample.domain.navigation.IUIActions
import com.google.credentialmanager.sample.presentation.core.navigation.NavigationImpl
import com.google.credentialmanager.sample.presentation.core.actions.UIActionImpl
import com.google.credentialmanager.sample.data.StringProviderImpl
import com.google.credentialmanager.sample.data.repository.FileRepositoryImpl
import com.google.credentialmanager.sample.domain.StringProvider
import com.google.credentialmanager.sample.domain.repository.FileRepository
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